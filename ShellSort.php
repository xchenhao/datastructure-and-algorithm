<?php
require './Tools.php';
$arr = getRandArray(10, 0, 10);
$arr_length = count($arr);
p($arr);
$h = 0;
while ($h <= $arr_length) {
    $h = $h * 3 + 1;
}
while ($h >= 1) {
    for ($i = $h; $i < $arr_length; $i ++)
    {
        $get = $arr[$i];
        $j = $i - $h;
        while ($j >= 0 && $arr[$j] > $get) {
            $arr[$j + $h] = $arr[$j];
            $j -= $h;
        }
        $arr[$j + $h] = $get;
    }
    $h = ($h - 1) / 3;
}

p($arr);