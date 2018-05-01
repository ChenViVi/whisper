<?php
require_once("config.php");

function check_ip($ip_list){
    return in_array($_SERVER['REMOTE_ADDR'], $ip_list);
}

function is_empty($C_char){
    if (empty($C_char)) return true; //判断是否已定义字符串
    if ($C_char=='') return true; //判断字符串是否为空
    return false;
}

function utf8_length($str){
    $count = 0;
    if (!empty($str) && $str != ''){
        preg_match_all('/[\x{4e00}-\x{9fa5}]/u', $str, $chinese);
        preg_match_all('/[^\x{4e00}-\x{9fa5}]/u', $str, $string);
        $str_array = array_merge(current($chinese), current($string));
        for($i=0;$i<count($str_array);$i++){
            if (strlen($str_array[$i]) == 1) $count = $count + 0.5;
            else $count = $count + 1;
        }
    }
    return $count;
}

function utf8_substring($str,$length){
    $result = '';
    $count = 0;
    if (!empty($str) && $str != ''){
        preg_match_all('/[\x{4e00}-\x{9fa5}]/u', $str, $chinese);
        preg_match_all('/[^\x{4e00}-\x{9fa5}]/u', $str, $string);
        $str_array = array_merge(current($chinese), current($string));
        for($i=0;$i<count($str_array) && $count < $length;$i++){
            if (strlen($str_array[$i]) == 1){
                $count = $count + 0.5;
            }
            else {
                $count = $count + 1;
            }
            $result = $result . $str_array[$i];
        }
    }
    return $result;
}