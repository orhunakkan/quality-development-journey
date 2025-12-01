'use strict';

function logger() {
    console.log('THIS IS ORHUN');
}

// calling / running / invoking function
logger();


function fruitProcessor(apples, oranges) {
    const juice = `Juice with ${apples} apples and ${oranges} oranges.`
    return juice;
}

const myJuice = fruitProcessor(5, 10);
console.log(myJuice);

// Function Declarations
function calcage1(birthYear) {
    const age = 2025 - birthYear;
    return age;
}

const age1 = calcage1(1990);
console.log(age1);

// Function Expressions -> with this you need to create the functions at the top so that you can call them later.

const calcage2 = function (birthYear) {
    return 2025 - birthYear;
}

const age2 = calcage2(1990);

console.log(age1, age2);

// Arrow functions

const calcAge3 = birthYear => 2025 - birthYear;
const age3 = calcAge3(1990);
console.log(age3);

const yearsUntilRetirement = (birthYear) => {
    const age = calcAge3(birthYear);
    const retirement = 65 - age;
    return retirement;
}

console.log(yearsUntilRetirement(1990));































