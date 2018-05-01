<?php
require_once("../func.php");
$status = 1;
$msg = "出现未知错误，去问问神奇的海螺吧";
$data = "";
$type = $_GET["type"];
$content = $_GET["content"];
if (!check_ip($IP_WHITE_LIST)){
    $msg = "你没有权限访问";
    $data = $_SERVER['REMOTE_ADDR'];
}
else if (is_empty($type) || is_empty($content)){
    $msg = "参数错误";
}
else {
    $mysqli=new mysqli($DB_HOST,$DB_USER,$DB_PASS,$DB_NAME,$DB_PORT);
    $mysqli->set_charset("utf8");
    if ($mysqli->connect_errno){
        $msg = "数据库连接失败，请检查 config.php 配置文件";
    }
    else{
        $stmt=$mysqli->prepare("SELECT * FROM type WHERE name = ?");
        $stmt->bind_param('s', $type);
        $stmt->execute();
        $result = $stmt->get_result();
        if (!($row = $result->fetch_assoc())){
            $stmt=$mysqli->prepare("INSERT INTO type(name) VALUES(?)");
            $stmt->bind_param('s', $type);
            $stmt->execute();
            $stmt=$mysqli->prepare("SELECT * FROM type WHERE name = ?");
            $stmt->bind_param('s', $type);
            $stmt->execute();
            $result = $stmt->get_result();
            $row = $result->fetch_assoc();
        }
        $stmt=$mysqli->prepare("INSERT INTO article(type_id,content) VALUES(?,?)");
        $stmt->bind_param('is', $row['id'], $content);
        $stmt->execute();
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