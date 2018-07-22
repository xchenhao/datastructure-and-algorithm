<?php
require './Tools.php';
$arr = getRandArray(20000, 0, 10);
//p($arr);
$T1 = microtime(true);
$arr_length = count($arr);
for ($i = 1; $i < $arr_length; $i ++ )
{
    $get = $arr[$i];
    $j = $i - 1;
    while ($j >= 0 && $arr[$j] > $get) {
        $arr[$j + 1] = $arr[$j];
        $j --;
    }
    $arr[$j + 1] = $get;
}
//p($arr);
$T2 = microtime(true);
echo $T2 - $T1;