// Remember, we're gonna use strict mode in all scripts now!
'use strict';

// PROBLEM:
// We work for a company building a smart home thermometer.
// Our most recent task is this:
// "Given an array of temperatures of one day, calculate the temperature amplitude.
// Keep in mind that sometimes there might be a sensor error."

const temperatures = [3, -2, -6, -1, 'error', 9, 13, 17, 15, 14, 9, 5];

// What to do for this problem
// How to ignore errors
// Find max value
// Find min value
// Subtract min form max and return it

function calculateAmplitude(temperatureArr) {
    const cleanedArr = [];
    for (let i = 0; i < temperatureArr.length; i++) {
        if (typeof temperatureArr[i] !== 'string') {
            cleanedArr.push(temperatureArr[i]);
        }
    }
    return (Math.max(...cleanedArr) - Math.min(...cleanedArr));
}

console.log(calculateAmplitude(temperatures));

