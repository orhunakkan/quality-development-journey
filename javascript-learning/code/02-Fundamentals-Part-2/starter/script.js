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

// Arrays

const friend1 = 'Orhun';
const friend2 = 'Rabia';
const friend3 = 'Raphia';

const friends = ['Orhun', 'Rabia', 'Raphia', 'Alya'];
console.log(friends);

const years = new Array(1990, 2000, 2010);
console.log(years);
console.log(years[0]);
console.log(years.length);

years[2] = 2020;
console.log(years);

const friendsAndNumbers = ['ORHUN', 1990];
console.log(friendsAndNumbers);

// Example:

const newCalcAge = function (birthYear) {
    return 2025 - birthYear;
}

const newYears = [1990, 1991, 1992, 1993, 1994, 1995];
const newFriends = ['Orhun', 'Rabia', 'Raphia', 'Alya'];

newYears.forEach(element => {console.log(newCalcAge(element))});

newFriends.push('Melek');
console.log(newFriends);

newFriends.unshift('Melek');
console.log(newFriends);

newFriends.pop();
console.log(newFriends);

newFriends.shift();
console.log(newFriends);

console.log(newFriends.indexOf('Orhun'));
console.log(newFriends.indexOf('WHO?'));

console.log(newFriends.includes('Orhun'));
console.log(newFriends.includes('WHO?'));

// Objects

// Object Literal Syntax -> the most basic way to create object
const orhun = {
    firstName: 'Orhun',
    lastName: 'Akkan',
    birthYear: 1990,
    job: 'IT',
    friends: newFriends,
    hasDriverslicense: true,
    calcAge: function () {
        console.log(this); // 'this' is the object (orhun) itself
        return 2025 - this.birthYear;
    }
}

console.log(orhun);
console.log(orhun.firstName);
console.log(orhun['firstName']);
console.log(orhun.friends);
console.log(orhun.book); // undefined

orhun.location = 'Schaumburg';
orhun['work'] = 'QA';

console.log(orhun);

console.log(orhun.calcAge());

// Loops

for (let i = 0; i < 10; i++) {
    console.log(i)
}

const orhunArray1 = ['Orhun', 35, 'QA', ['raphia', 'alya']];
const types = [];

for(let i = 0; i < orhunArray1.length; i++) {
    console.log(orhunArray1[i], typeof orhunArray1[i]);
    types.push(typeof orhunArray1[i]);
}

console.log(types);

// continue and break

for (let i = 0; i < 10; i++) {
    if (i === 3 || i === 4) continue;
    else console.log(i);
}

console.log('-------------------------------')

for (let i = 0; i < 10; i++) {
    if (i === 5) break;
    else console.log(i);
}

console.log('-------------------------------')

// Looping backwards

const orhunArray2 = ['Orhun', 35, 'QA', ['raphia', 'alya']];

for (let i = orhunArray2.length-1; i >= 0; i--) {
    console.log(i, orhunArray2[i]);
}

// Inner Loops

for (let exercise = 1; exercise < 4; exercise++) {
    console.log(`----- Starting Exercise ${exercise} -----`)

    for (let rep = 1; rep < 6; rep++) {
        console.log(`Lifting weight repetition ${rep} 💪️`)
    }
}

console.log('-------------------------------')

// While Loops

let rep = 1;

while (rep <= 10) {
    console.log(`Lifting weight repetition ${rep} 🏋️`);
    rep++;
}

console.log('-------------------------------')

// Dice is a random number of 1 to 6

let dice = Math.trunc(Math.random() * 6) + 1;

while (dice !== 6) {
    console.log(`You rolled a ${dice}`);
    dice = Math.trunc(Math.random() * 6) + 1;
}
































