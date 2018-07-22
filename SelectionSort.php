<?php
require './Tools.php';
$arr = getRandArray(10000, 0, 10);
// p($arr);
$T1 = microtime(true);
$arr_length = count($arr);
for ($i = 0; $i < $arr_length - 1; $i ++)
{
    $min = $i;
    for ($j = $i + 1; $j < $arr_length; $j ++)
    {
        if ($arr[$j] < $arr[$min]) {
            $min = $j;
        }
    }
    if ($min != $i) {
        swap($arr[$min], $arr[$i]);
    }
}
$T2 = microtime(true);
// p($arr);
echo $T2 - $T1;
