<?php
require './Tools.php';
$arr = getRandArray(10000, 0, 10);
// p($arr);
$arr_length = count($arr);
$T1 = microtime(true);
for ($i = 0; $i < $arr_length; $i ++)
{
    for ($j = 0; $j < $arr_length - 1 - $i; $j ++)
    {
        if ($arr[$j] > $arr[$j + 1]) {
            swap($arr[$j], $arr[$j + 1]);
        }
    }
    // pArray($arr);
    // echo "\n\r";
}
$T2 = microtime(true);
// p($T2);
// p($T1);
echo $T2 - $T1;
// p($arr);