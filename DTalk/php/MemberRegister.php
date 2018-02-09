<?php
    $con = mysqli_connect("localhost","tpwls8122","wls8550!","tpwls8122");

    $s_id = $_POST["s_id"];
    $s_password = $_POST["s_password"];
    $s_name = $_POST["s_name"];
    $s_major = $_POST["s_major"];
    $s_group = $_POST["s_group"];
    $s_phone = $_POST["s_phone"];


    $statement = mysqli_prepare($con,"INSERT INTO Student VALUES (?,?,?,?,?,?)");
    mysqli_stmt_bind_param($statement,"ssssss",$s_id,$s_password,$s_name,$s_major,$s_group,$s_phone);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);

?>