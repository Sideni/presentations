<?php
//SELECT POW (count,1) FROM (SELECT AVG (c) count, AVG (c) FROM (SELECT b-a
// as c FROM (SELECT UUID_SHORT ()b, a FROM information_schema.tables, (SELECT UUI
// D_SHORT ()a)x)y)asd)uaj

session_start();

$dbms = 'mysql';
$host = 'localhost';
$port = 3306;
$dbName = 'chal16DB';
$username = 'chal16';
$password = 'chal16';

try {
	$db = new PDO("$dbms:host=$host;dbname=$dbName;charset=utf8", $username, $password);	
} catch (PDOException $e) {
	echo "Connection failed : " . $e->getMessage();
}

if (isset($_GET['colA'])) {
    $colA = $_GET['colA'];
	$pattern = '/(;|=|@|div|rows_found|sum|count|group_concat|length|max|\/|MOD|%|\*|[a-zA-Z]\()/i';
	if(preg_match($pattern, $colA)){
		$errorMsg = "You has no rights to use these !";
    } else {
        $count = rand(1,5000);
        $sql_alter = "ALTER VIEW suchTable AS SELECT * FROM large LIMIT $count";
        $db->query($sql_alter);

        $sql = "SELECT $colA as a FROM suchTable LIMIT 1";
		$resultSet = $db->query($sql);
		if (!$resultSet) {
			$errorMsg = $db->errorInfo()[2];
            $errorMsg .= "<br>The query is : $sql";
		} else {
			$row = $resultSet->fetch(PDO::FETCH_ASSOC);
            if ($row) {
                $a = $row['a'];
                if ($count == $a)
                    $_SESSION['solved16'] = true;
                else
				    $errorMsg = 'That\'s not it...';
			} else {
				$errorMsg = 'That\'s not it...';
			}
		}
	}
} else {
    header("location: chal16.php?colA=a");
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
	<div class="logo">Brain teaser 2</div>
	<div class="login-form-1">
		<form id="login-form" class="text-left" method="get">
			<div class="login-form-main-message"></div>
			<div class="main-login-form">
				<div class="login-group">
					<div class="form-group">
						<input type="text" class="form-control" id="lg_username" name="colA" placeholder="a">
					</div>
				</div>
				<input type="submit" class="login-button" value=">">
				
			</div>
		</form>
    </div>
    <div class="text-center">
        The goal of this challenge is to have the COUNT() of the table 'suchTable' which only has the column 'a'.</br>
        Why ?</br>
        .....</br>
        For the glory of Satan, of course !
    </div>
    <div id="a" class="text-left">
    Column A first entry : <b><?php echo $a; ?></b>
    </div>

	<?php if ($_SESSION['solved16'] === true) { ?>
        <div class="alert alert-success">Hai there <?php echo $_SESSION['username']; ?>, you is so good, here's a flag !
        </br>
        FLAG-0n3_TW0_thR33_..._D4mN_..._!t_cH4nG3d!!!
        </div>
	<?php }
	 if ($errorMsg) { ?>
		<div class="alert alert-danger"><?php echo $errorMsg; ?></div>
	<?php } ?>

</div>
<small>By the way, the query limits the number of rows returned to 1</small>
</BODY>
</HTML>

