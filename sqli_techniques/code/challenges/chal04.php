<?php
// CLASSIC LOGIN BYPASS WITH FILTERS

session_start();

$dbms = 'mysql';
$host = 'localhost';
$port = 3306;
$dbName = 'chal04DB';
$username = 'chal04';
$password = 'chal04';

try {
	$db = new PDO("$dbms:host=$host;dbname=$dbName;charset=utf8", $username, $password);	
} catch (PDOException $e) {
	echo "Connection failed : " . $e->getMessage();
}

if (isset($_POST['username']) && isset($_POST['password'])) {
	$uname = $_POST['username'];
	$passwd = $_POST['password'];
	$pattern = '/(\s|\x0a|like|<|>|true|[0-9a-zA-Z]|and|or|=|#|--|\/\*|\*\/)/i';
	if(preg_match($pattern, $uname) || preg_match($pattern, $passwd)){
		$errorMsg = "You has no rights to use these !";
	} else {	
		$sql = "SELECT * FROM users WHERE username = '" . $uname . "' AND password = '" . $passwd . "'";
		$resultSet = $db->query($sql);
		if (!$resultSet) {
			$errorMsg = $db->errorInfo()[2];
		} else {
			$row = $resultSet->fetch(PDO::FETCH_ASSOC);
			if ($row) {
				$_SESSION['username'] = $row['username'];
				$_SESSION['solved'] = true;
			} else {
				$errorMsg = 'Wrong username/password ...';
			}
		}
	}
}
?>

<HTML>
<link rel="stylesheet" href="style.css" type="text/css" >
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

<BODY>
<div>Go back to <a href="./chals.php">los challengos</a> !</div>
<div class="text-center" style="padding:50px 0">
	<div class="logo">Portal 4 Much Secure</div>
	<div class="login-form-1">
		<form id="login-form" class="text-left" method="post">
			<div class="login-form-main-message"></div>
			<div class="main-login-form">
				<div class="login-group">
					<div class="form-group">
						<input type="text" class="form-control" id="lg_username" name="username" placeholder="username">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="lg_password" name="password" placeholder="password">
					</div>
				</div>
				<input type="submit" class="login-button" value=">">
				
			</div>
		</form>
	</div>

	<?php if ($_SESSION['solved'] === true) { ?>
        <div class="alert alert-success">Hai there <?php echo $_SESSION['username']; ?>, you is so good, here's a flag !
        </br>
        FLAG-ByP455_4ll_tH3m_th!NG5!!!
        </div>
	<?php }
	 if ($errorMsg) { ?>
		<div class="alert alert-danger"><?php echo $errorMsg; ?></div>
	<?php } ?>

</div>
</BODY>
</HTML>

