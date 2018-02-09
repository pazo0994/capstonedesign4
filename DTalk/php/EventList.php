<?php
$con = mysqli_connect("localhost","tpwls8122","wls8550!","tpwls8122");

$result = mysqli_query($con,"SELECT * FROM Event;");
$response = array();

while($row = mysqli_fetch_array($result)){
    array_push($response,array("event_title"=>$row[0],"event_content"=>$row[1],"event_name"=>$row[2],"event_date"=>$row[3]));
}

echo json_encode(array("response"=>$response));
mysqli_close($con);

?>