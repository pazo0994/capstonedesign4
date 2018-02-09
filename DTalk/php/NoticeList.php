<?php
$con = mysqli_connect("localhost","tpwls8122","wls8550!","tpwls8122");

$result = mysqli_query($con,"SELECT * FROM Notice ORDER BY notice_date DESC;");
$response = array();

while($row = mysqli_fetch_array($result)){
    array_push($response,array("notice_title"=>$row[0],"notice_content"=>$row[1],"notice_name"=>$row[2],"notice_date"=>$row[3]));
}

echo json_encode(array("response"=>$response));
mysqli_close($con);

?>