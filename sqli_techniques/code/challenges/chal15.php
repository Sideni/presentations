<?php
// +, -
// SELECT UNIX_TIMESTAMP (TIMESTAMPADD (SECOND, a, FROM_UNIXTIME (b))) as a, TIMESTAMPDIFF (SECOND, FROM_UNIXTIME (a), FROM_UNIXTIME (b)) as b FROM (SELECT 2 a, 123 b)t;

session_start();

$dbms = 'mysql';
$host = 'localhost';
$port = 3306;
$dbName = 'chal15DB';
$username = 'chal15';
$password = 'chal15';

try {
	$db = new PDO("$dbms:host=$host;dbname=$dbName;charset=utf8", $username, $password);	
} catch (PDOException $e) {
	echo "Connection failed : " . $e->getMessage();
}

if (isset($_GET['colA']) && isset($_GET['colB'])) {
    $colA = $_GET['colA'];
    $colB = $_GET['colB'];
	$pattern = '/(;|-|\+|=|@|div|\/|MOD|%|\*|[a-zA-Z]\()/i';
	if(preg_match($pattern, $colA) || preg_match($pattern, $colB)){
		$errorMsg = "You has no rights to use these !";
    } else {
        $a = rand(10,100000);
        $b = rand(10,100000);
        $sql = "SELECT $colA as a, $colB as b FROM (SELECT $a a, $b b) t";
		$resultSet = $db->query($sql);
		if (!$resultSet) {
            $errorMsg = $db->errorInfo()[2];
            $errorMsg .= "<br>The query is : $sql";
		} else {
			$row = $resultSet->fetch(PDO::FETCH_ASSOC);
            if ($row) {
                $sum = $row['a'];
                $dif = $row['b'];
                if ($sum == $a + $b && $dif == $b - $a)
                    $_SESSION['solved15'] = true;
                else
				    $errorMsg = 'That\'s not it...';
			} else {
				$errorMsg = 'That\'s not it...';
			}
		}
	}
} else {
    header("location: chal15.php?colA=a&colB=b");
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
	<div class="logo">Brain teaser 1</div>
	<div class="login-form-1">
		<form id="login-form" class="text-left" method="get">
			<div class="login-form-main-message"></div>
			<div class="main-login-form">
				<div class="login-group">
					<div class="form-group">
						<input type="text" class="form-control" id="lg_username" name="colA" placeholder="a">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="lg_password" name="colB" placeholder="b">
					</div>
				</div>
				<input type="submit" class="login-button" value=">">
				
			</div>
		</form>
    </div>
    <div class="text-center">
        The goal of this challenge is to have the sum of column 'a' and 'b'(a+b) in the column 'a'</br>
        and the difference of 'a' and 'b'(b-a) in the column 'b'...</br>
        Why ?</br>
        .....</br>
        For the glory of Satan, of course !
    </div>
    <div id="a" class="text-left">
    Column A : <b><?php echo $sum; ?></b>
    </div>
    <div id="b" class="text-left">
    Column B : <b><?php echo $dif; ?></b>
    </div>

	<?php if ($_SESSION['solved15'] === true) { ?>
        <div class="alert alert-success">Hai there <?php echo $_SESSION['username']; ?>, you is so good, here's a flag !
        </br>
        FLAG-G0d_h0W_W!11_th!5_B3_U53FuL?!
        </div>
	<?php }
	 if ($errorMsg) { ?>
		<div class="alert alert-danger"><?php echo $errorMsg; ?></div>
	<?php } ?>

</div>
<small>In order to not let you go on the wrong track the table 't' doesn't exist for real, it's built from a subquery...</small>
</BODY>
</HTML>

