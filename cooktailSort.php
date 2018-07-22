<?php

require './Tools.php';
$arr = getRandArray(10000, 0, 10);
// p($arr);
$arr_length = count($arr);

$T1 = microtime(true);
$left = 0;
$right = $arr_length - 1;
while ($left < $right)
{
    for ($i = $left; $i < $right; $i ++)
    {
        if ($arr[$i] > $arr[$i + 1]) {
            swap($arr[$i], $arr[$i + 1]);
        }
    }
    $right --;
    for ($j = $right; $j > $left; $j --)
    {
        if ($arr[$j - 1] > $arr[$j]) {
            swap($arr[$j - 1], $arr[$j]);
        }
    }
    $left ++;
}

$T2 = microtime(true);
echo $T2 - $T1;
// p($arr);