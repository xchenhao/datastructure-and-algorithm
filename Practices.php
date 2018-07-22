<?php
require './Tools.php';
$arr = getRandArray(10, 0, 10);
$arr_length = count($arr);
p($arr);
/*
// 冒泡排序
for ($i = 0; $i < $arr_length; $i ++) {
    for ($j = 0; $j < $arr_length - 1 - $i; $j ++) {
        if ($arr[$j] > $arr[$j + 1]) {
            swap($arr[$j], $arr[$j + 1]);
        }
    }
}
p($arr);
*/
/*
// 鸡尾酒排序
$left = 0;
$right = $arr_length - 1;
while ($right > $left) {
    for ($i = 0; $i < $right; $i ++) {
        if ($arr[$i] > $arr[$i + 1]) {
            swap($arr[$i], $arr[$i + 1]);
        }
    }
    $right --;
    for ($j = $right; $j > $left; $j --) {
        if ($arr[$j - 1] > $arr[$j]) {
            swap($arr[$j - 1], $arr[$j]);
        }
    }
    $left ++;
}
p($arr);
*/
/*
// 选择排序
for ($i = 0; $i < $arr_length; $i ++) {
    $min = $i;
    for ($j = $i + 1; $j < $arr_length; $j ++) {
        if ($arr[$j] < $arr[$min]) {
            $min = $j;
        }
    }
    if ($min != $i) {
        swap($arr[$min], $arr[$i]);
    }
}
p($arr);
*/
/*
// 插入排序
for ($i = 1; $i < $arr_length; $i ++) {
    $get = $arr[$i];
    $j = $i - 1;
    while ($j >= 0 && $arr[$j] > $get) {
        $arr[$j + 1] = $arr[$j];
        $j --;
    }
    $arr[$j + 1] = $get;
}
p($arr);
*/















