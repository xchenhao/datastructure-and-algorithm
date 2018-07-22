<?php
// https://www.cnblogs.com/eniac12/p/5329396.html#s32

function swap(&$var1, &$var2)
{
    $temp = $var1;
    $var1 = $var2;
    $var2 = $temp;
}

function getRandArray(int $length = 1, int $min = 0, int $max = 1): array
{
    $array = range(0, $length - 1);
    foreach ($array as &$v)
    {
        $v = rand(0, $max);
    }


    return $array;
}

function p($var)
{
    if (is_bool($var) || is_null($var)) {
        var_dump($var);
    } else {
        echo '<pre>';
        print_r($var);
    }
    // exit;
}

function pArray($array)
{
    foreach ($array as $v) {
        echo $v . ' ';
    }
}