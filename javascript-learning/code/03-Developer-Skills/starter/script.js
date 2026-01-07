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

// Problem 2:
// Function should now receive 2 arrays of temperatures

// 1) Understanding the problem
// - With 2 arrays, should we implement functionality twice? No! Just merge two arrays

// 2) Breaking up into sub-problems
// - Merge 2 arrays
// - Send merged array to amplitude function

// const temps1 = [3, 5, 1, 9];
// const temps2 = [4, 0, -3, 12];
// const temps3 = temps1.concat(temps2);

// const measureKelvin = function () {
//     const measurement = {
//         type: 'temp',
//         unit: 'celcius',
//         value: prompt('Degrees Celcius:')
//     }

//     const kelvin = parseInt(measurement.value) + 273;
//     return kelvin;
// }

// console.log(measureKelvin());

// Coding Challenge #1

/*
Given an array of forecasted maximum temperatures,
the thermometer displays a string with these
temperatures.

Example: [17, 21, 23] will print "... 17°C in 1 days ...
21°C in 2 days ... 23°C in 3 days ..."

Create a function 'printForecast' which takes in an
array 'arr' and logs a string like the above to the
console.

Use the problem-solving framework: Understand the
problem and break it up into sub-problems!

TEST DATA 1: [17, 21, 23]
TEST DATA 2: [12, 5, -5, 0, 4]
*/

const testData1 = [17, 21, 23];
const testData2 = [12, 5, -5, 0, 4];

function printForecast (arr) {
    let wholeString = '';
    for (let i = 0; i < arr.length; i++) {
        wholeString += `... ${arr[i]}°C in ${i+1} days `;
    }
}

console.log(printForecast(testData2));