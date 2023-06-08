<?php
// CODE EXECUTION PGSQL, GET yet_again_another_secure_flag_file
// ';' is required
session_start();

$dbms = 'pgsql';
$host = 'localhost';
$dbName = 'chal12db';
$username = 'chal12';
$password = 'chal12';

try {
	$db = pg_pconnect("host=localhost dbname=$dbName user=$username password=$password");	
} catch (Exception $e) {
	echo "Connection failed : " . $e->getMessage();
}

$author = $_POST['author'];
$pattern = '/(LOAD)/i';
if(preg_match($pattern, $author)){
	$errorMsg = "You has no rights to use these !";
} else {	
	$msgSet = pg_query($db, "SELECT * FROM msg WHERE author LIKE '%" . $author  . "%' ORDER BY date DESC");
	if (!$msgSet) {
		$errorMsg = pg_last_error($db);
	} else {
		$msg = pg_fetch_all($msgSet);
	}
}

$userSet = pg_query($db, "SELECT username FROM users");
$users = pg_fetch_all($userSet);

?>

<HTML>
<!-- All the files that are required -->
<link rel="stylesheet" href="style.css" type="text/css" >
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<!-- Logs : &l0gz=1 -->
<?php
if(isset($_POST['l0gz']) && $_POST['l0gz'] === '1') {
    $nb_lines = 20;
    echo "<!--";
    $file = file("/var/log/postgresql/postgresql-9.5-main.log");
    for ($i = max(0, count($file)-$nb_lines); $i < count($file); $i++) {
        if ($i > 0)
            echo $file[$i];
    }
    echo "-->";
}
?>
<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

<BODY>
<div style="padding:50px 50px">
	<div class="logo">Messages sent by the users</div>
	<div style="text-align: right">
		<form method="post">
			<select class='login-group' name='author'>
				<option value=''>All users</option>
<?php			foreach ($users as $user) {
				echo "<option value='" . $user['username'] . "'>" . $user['username'] . "</option>";
			}
?>
			</select>
			<input class="btn" type="submit" value="Load user's messages" />
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
