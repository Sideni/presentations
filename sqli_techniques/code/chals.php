<?php
session_start();
?>

<HTML>
<!-- All the files that are required -->
<link rel="stylesheet" href="list.css" type="text/css" >
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

<BODY>
<div style="padding:50px 50px">
	<div class="logo">Los Challengos</div>
<?php
$flags = array(
    "chal01,FLAG-Ze_1st_!_ZE_L345T,no",
    "chal02,FLAG-50m3_f!lt3R5_n3v4_HuRT,no",
    "chal03,FLAG-Wut_U_D0!NG_ST4hP!,no",
    "chal04,FLAG-ByP455_4ll_tH3m_th!NG5!!!,yes",
    "chal05,FLAG-U_C0u1D_4dd_4Ny7h!N6_!N_Th3R3,yes",
    "chal06,FLAG-5h0w_z3_3rr0rs_2_L3T_p33ps_kn0W_i7_41NT_w0rkING,yes",
    "chal07,FLAG-Much0_3rr0r5_w/_My5Q1,no",
    "chal08,FLAG-1337_UN10N_1nJ3kt!,no",
    "chal09,FLAG-1337_H4ck3r_but_wi11_U_B_4Bl3_2_do_LS_n3xT?,yes",
    "chal10,FLAG-ElBl1nDSQL!DelBooL3ano,no",
    "chal11,FLAG-RC3_!5_Z3_B35T,no",
    "chal12,FLAG-MuCH_N!c3_2_U53_53m!-C010Nz,yes",
    "chal13,FLAG-I_C0u1D_M4k3_4_V3ry_10NG_Fl4G...V3ry_V3ry_V3ry_V3Ry_L0nG:tr0llf4ce:,yes",
    "chal14,FLAG-t!m3y_w!m3y_w!bb1y_w0bb1y_5tuff,yes",
    "chal15,FLAG-G0d_h0W_W!11_th!5_B3_U53FuL?!,yes",
    "chal16,FLAG-0n3_TW0_thR33_..._D4mN_..._!t_cH4nG3d!!!,yes"
);

if (isset($_POST['flag'])) {
    for($i = 0; $i < count($flags); $i++){
        $chal_data = explode(',',$flags[$i]);
        $flag = $chal_data[1];
        if($flag === $_POST['flag']) {
            $_SESSION['solved' . $i] = true;
            break;
        }
    }
}

for ($i = 0; $i < count($flags); $i++) {
    $chal_data = explode(',',$flags[$i]);
    $chal = $chal_data[0];
    $flag = $chal_data[1];
    $interesting = $chal_data[2];
    
    $port = strval(20001 + $i);
    if ($i == 15)
        $port = strval(20000 + $i);
    $url = 'http://challenges.ringzer0team.com:' . $port . '/' . $chal . '.php';
    echo "<a href='$url'>";
	if ($_SESSION['solved' . $i] === true)
		echo '<div class="login-group-success">';
	else
        echo '<div class="login-group">';
    
    $title = $chal;
    if ($interesting === 'yes')
        $title = $chal . ' *';
    
	echo	'<div class="author">' . $title . '</div>';
	echo '</div>';
	echo "</a>";
}
?>
</div>
<form id="login-form" class="text-left" method="post">
    <div class="main-login-form">
        <div class="login-group">
            <div class="form-group">
            <input type="text" class="form-control" id="lg_username" name="flag" placeholder="Enter your flag">
            </div>
        </div>
        <input type="submit" class="login-button" value=">">
    </div>	
</form>
</br>
</br>
</br>
</br>
</br>
</br>
<small>* Challenges that are thought to be a bit more interesting over their category...</small>
</BODY>
</HTML>

