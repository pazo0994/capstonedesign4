<?php
    $con = mysqli_connect("localhost","tpwls8122","wls8550!","tpwls8122");
    
    $s_id = $_POST["s_id"];
    
    $statement = mysqli_prepare($con,"SELECT * FROM Student WHERE s_id = ?");

    mysqli_stmt_bind_param($statement,"s",$s_id);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement,$s_id);
    
    $response = array();
    $response["success"] = true;
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = false;
        $response["s_id"] = $s_id;
    }
    
    echo json_encode($response);

?>