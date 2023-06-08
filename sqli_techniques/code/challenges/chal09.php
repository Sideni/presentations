<?php
// FLAG IN THIS FILE LOAD_FILE()

session_start();

$dbms = 'mysql';
$host = 'localhost';
$dbName = 'chal09DB';
$username = 'chal09';
$password = 'chal09';

try {
	$db = new PDO("$dbms:host=$host;dbname=$dbName;charset=utf8", $username, $password);	
} catch (PDOException $e) {
	echo "Connection failed : " . $e->getMessage();
}

$author = $_GET['author'];
$pattern = '/(9|into|outfile)/i';
if(preg_match($pattern, $author)){
	$errorMsg = "You has no rights to use these !";
} else {	
	$msgSet = $db->query("SELECT * FROM msg WHERE author LIKE '%" . $author  . "%' ORDER BY date DESC;");
	if (!$msgSet) {
		$errorMsg = "Why would you show backend errors ?!";
	} else {
		$msg = $msgSet->fetchAll();
	}
}

if (isset($_POST['flag'])) {
	if ('FLAG-1337_H4ck3r_but_wi11_U_B_4Bl3_2_do_LS_n3xT?' === $_POST['flag']) {
		echo "<div class='alert alert-success'>Yeah, it seems like it's a valid one !</div>";
    } else {
        echo "<div class='alert alert-error'>Nope, keep trying though !</div>";
    }
}

$userSet = $db->query("SELECT username FROM users");
$users = $userSet->fetchAll();

?>

<HTML>
<!-- All the files that are required -->
<link rel="stylesheet" href="style.css" type="text/css" >
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

<BODY>
<div>Go back to <a href="./chals.php">los challengos</a> !</div>
<div style="padding:50px 50px">
	<div class="logo">Messages sent by the users</div>
	<div style="text-align: right">
		<form method="get">
			<select class='login-group' name='author'>
				<option value=''>All users</option>
<?php			foreach ($users as $user) {
				echo "<option value='" . $user['username'] . "'>" . $user['username'] . "</option>";
			}
?>
			</select>
			<input class="btn" type="submit" value="Load user's messages" />
		</form>
		<form id="login-form" class="text-left" method="post">
			<input type="text" class="form-control" id="lg_username" name="flag" placeholder="Is that a valid flag ?">
			<input type="submit" class="login-button" value="Submit flag">
		</form>
	</div>
<?php 
if ($msg) {
	echo '<div class="login-group">';

	$i = 0;
	foreach ($msg as $m) {
		$i++;
		if ($i == count($msg))
			echo '<div>';
		else
			echo '<div class="msg-group">';
		echo	'<div class="author">' . $m['author'] . '</div>';
		echo	'<div class="date">' .  $m['date'] . '</div>';
		echo 	'<div class="msg">' . $m['text'] . '</div>';
		echo '</div>';
	}
	echo '</div>';
} else {
	if (!$errorMsg)
		$errorMsg = 'There are no messages...';
}
?>
	<?php if ($errorMsg) { ?>
	<div class="alert alert-danger">
	<?php echo $errorMsg; ?>
	</div>
	<?php } ?>
</div>
</BODY>
</HTML>

