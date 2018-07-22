<?php
require './Tools.php';
$arr = getRandArray(10000, 0, 10);
$arr_length = count($arr);
$T1 = microtime(true);
for ($i = 1; $i < $arr_length; $i ++)
{
    $get = $arr[$i];
    $left = 0;
    $right = $i - 1;
    while ($left <= $right) {
        $mid = intval(($left + $right) / 2);
        if ($arr[$mid] > $get) {
            $right = $mid - 1;
        } else {
            $left = $mid + 1;
        }
    }
    for ($j = $i - 1; $j >= $left; $j --) {
        $arr[$j + 1] = $arr[$j];
    }
    $arr[$left] = $get;
}
$T2 = microtime(true);
echo $T2 - $T1;