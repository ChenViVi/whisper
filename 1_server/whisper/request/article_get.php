<?php
require_once("../func.php");
$status = 1;
$msg = "出现未知错误，去问问神奇的海螺吧";
$data = "";
$type_id = $_GET["type_id"];
if (!check_ip($IP_WHITE_LIST)){
    $msg = "你没有权限访问";
    $data = $_SERVER['REMOTE_ADDR'];
}
else if (is_empty($type_id)){
    $msg = "参数错误";
}
else {
    $mysqli=new mysqli($DB_HOST,$DB_USER,$DB_PASS,$DB_NAME,$DB_PORT);
    $mysqli->set_charset("utf8");
    if ($mysqli->connect_errno){
        $msg = "数据库连接失败，请检查 config.php 配置文件";
    }
    else{
        $data = array();
        if ($type_id == -1){
            $stmt=$mysqli->prepare("SELECT article.id,type_id,type.name AS type_name,content,time FROM article JOIN type WHERE article.type_id = type.id ORDER BY article.id DESC ");
            $stmt->execute();
            $result = $stmt->get_result();
            while ($row = $result->fetch_assoc()) {
                array_push($data,$row);
            }
        }
        else {
            $stmt=$mysqli->prepare("SELECT article.id,type_id,type.name AS type_name,content,time FROM article JOIN type WHERE article.type_id = type.id AND type_id = ? ORDER BY article.id DESC");
            $stmt->bind_param('i', $type_id);
            $stmt->execute();
            $result = $stmt->get_result();
            while ($row = $result->fetch_assoc()) {
                array_push($data,$row);
            }
        }
        $status = 0;
        $msg = "查询成功";
    }
    mysqli_close($mysqli);
}
echo json_encode(array(
    'status' => $status,
    'msg' => $msg,
    'data' => $data,
));