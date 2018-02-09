<?php
$con = mysqli_connect("localhost","tpwls8122","wls8550!","tpwls8122");

$s_id = $_POST["s_id"];
$s_password = $_POST["s_password"];

$statement = mysqli_prepare($con,"SELECT * FROM Student WHERE s_id = ? AND s_password = ? ");
mysqli_stmt_bind_param($statement,"ss",$s_id,$s_password);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement,$s_id);

$response = array();
$response["success"] = false;

while(mysqli_stmt_fetch($statement)){
    $response["success"] = true;
    $response["s_id"] = $s_id;
}

echo json_encode($response);

?>