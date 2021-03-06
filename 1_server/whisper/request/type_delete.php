<?php
require_once("../func.php");
$status = 1;
$msg = "出现未知错误，去问问神奇的海螺吧";
$data = "";
$id = $_GET["id"];
if (!check_ip($IP_WHITE_LIST)){
    $msg = "你没有权限访问";
    $data = $_SERVER['REMOTE_ADDR'];
}
else if (is_empty($id)){
    $msg = "参数错误";
}
else{
    $mysqli=new mysqli($DB_HOST,$DB_USER,$DB_PASS,$DB_NAME,$DB_PORT);
    $mysqli->set_charset("utf8");
    if ($mysqli->connect_errno){
        $msg = "数据库连接失败，请检查 config.php 配置文件";
    }
    else{
        $stmt=$mysqli->prepare("DELETE FROM type WHERE id = ?");
        $stmt->bind_param('i', $id);
        $stmt->execute();
        $status = 0;
        $msg = "删除成功";
    }
    mysqli_close($mysqli);
}
echo json_encode(array(
    'status' => $status,
    'msg' => $msg,
    'data' => $data,
));