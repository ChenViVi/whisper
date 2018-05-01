<?php
require_once("config.php");
$mysqli=new mysqli($DB_HOST,$DB_USER,$DB_PASS,$DB_NAME,$DB_PORT);
$mysqli->set_charset("utf8");?>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><?php echo $TITLE?></title>
    <script src="js/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="css/materialize.min.css">
    <script src="js/materialize.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">
    <style>
        .tabs .indicator {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
    <nav class="nav-extended pink accent-1">
        <div class="nav-wrapper">
            <a href="#" class="brand-logo center"><?php echo $TITLE?></a>
            <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="fas fa-bars"></i></a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a href="https://github.com/ChenViVi"><i class="fab fa-github"></i></a></li>
                <li><a href="https://weibo.com/p/1005053101937447/home?from=page_100505&mod=TAB&is_all=1"><i class="fab fa-weibo"></i></a></li>
                <li><a href="https://steamcommunity.com/profiles/76561198300793241"><i class="fab fa-steam"></i></a></li>
            </ul>
            <ul class="side-nav" id="mobile-demo">
                <li><a href="https://github.com/ChenViVi"><i class="fab fa-github"></i></a></li>
                <li><a href="https://weibo.com/p/1005053101937447/home?from=page_100505&mod=TAB&is_all=1"><i class="fab fa-weibo"></i></a></li>
                <li><a href="https://steamcommunity.com/profiles/76561198300793241"><i class="fab fa-steam"></i></a></li>
            </ul>
        </div>
        <div class="nav-content">
            <ul class="tabs pink accent-1">
                <?php
                $types = array();
                $stmt=$mysqli->prepare("SELECT * FROM type ORDER BY id");
                $stmt->execute();
                $result = $stmt->get_result();?>
                <?php while ($row = $result->fetch_assoc()) {
                    array_push($types,$row['id']);?>
                <li class="tab"><a href="#tab<?php echo $row['id']?>" class="white-text active" style="text-transform: none !important"><?php echo $row['name']?></a></li>
                <?php }?>
                <li class="indicator white" style="right: 186px; left: 68px;"></li>
            </ul>
        </div>
    </nav>
    <?php for ($i = 0; $i < count($types); $i++){
        $stmt=$mysqli->prepare("SELECT * FROM article WHERE type_id = ? ORDER BY id DESC");
        $stmt->bind_param('i', $types[$i]);
        $stmt->execute();
        $result = $stmt->get_result();?>
    <div id="tab<?php echo $types[$i]?>" class="active " style="margin-top: 20px;">
        <?php while ($row = $result->fetch_assoc()) {?>
        <div  style="margin-left: 20%;margin-right: 20%;">
            <div class="card-panel">
                <span><?php echo $row['content']?></span>
                <br/>
                <span class="right"><?php echo $row['time']?></span>
            </div>
        </div>
        <?php }?>
    </div>
    <?php }?>
    <?php mysqli_close($mysqli);?>
</body>
</html>