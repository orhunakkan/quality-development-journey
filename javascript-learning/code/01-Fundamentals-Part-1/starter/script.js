let js = 'Consistency is key!';
console.log(40 + 10);

let firstName = 'ORHUN';
console.log(firstName);

// ----- Primitive Data Types -----

let xage = 35;
let myName = 'ORHUN';
let isGoodAtJavascript = false;
let thisIsUndefined;
// null
// symbol -> ES2015
// BigInt -> ES2020

console.log(typeof xage);
console.log(typeof myName);
console.log(typeof isGoodAtJavascript);

console.log(isGoodAtJavascript);
isGoodAtJavascript = 'YES';
console.log(isGoodAtJavascript);

console.log(thisIsUndefined);
console.log(typeof thisIsUndefined);

thisIsUndefined = 'Not anymore';
console.log(thisIsUndefined);
console.log(typeof thisIsUndefined);

console.log(typeof null);

// ----- let, const, var -----

let age = 35;
const birthYear = 1990;
// const job; -> this is not allowed

var job = 'programmer';
job = 'teacher';
console.log(job);

// Math Operators
const now = 2050;
const ageOrhun = now - 1990;
const ageRabia = now - 1992;
console.log(ageOrhun, ageRabia);
console.log(ageOrhun * 2, ageRabia / 2);

const myFirstName = 'ORHUN';
const myLastName = 'AKKAN';
console.log(myFirstName + ' ' + myLastName);

// Assignment Operators
let x = 15;
x += 10;
x *= 5;
x++;
x--;
console.log(x);

// Comparison Operators
console.log(ageOrhun > ageRabia); // >, <, >=, <=

// Operator Precedence is followed in calculations.

// Template Literals
const orhun = `My name is ${myFirstName} and my last name is ${myLastName}`;
console.log(orhun);

// Decision-Making
const myAge = 15;
const isOldEnough = myAge >= 18;

if (isOldEnough) {
    console.log(`Orhun can start driving`);
} else {
    console.log(`Nope, not yet`);
}

let century;
const yourBirthYear = 1990;

if (yourBirthYear <= 2000) {
    century = 20;
} else {
    century = 21;
}

console.log(century);

// Type Conversion

const inputYear = '1990';
console.log(Number(inputYear) + 10);

console.log(Number('ORHUN')); // This gives NaN: Not a Number
console.log(String(35)); // This converts 35 to string

// Type Coercion

console.log('I am ' + 35 + ' years old');
console.log('35' - '10' - 10); // 15

let n = '1' + 1; // 11
n = n - 1; // 10
console.log(n);

// 5 falsy values: 0, '', undefined, null, NaN

console.log(Boolean(0));
console.log(Boolean(''));
console.log(Boolean(undefined));
console.log(Boolean(null));
console.log(Boolean(NaN));

console.log(Boolean('ORHUN'));
console.log(Boolean(35));

const money = 100;

if (money) {
    console.log('DO NOT SPEND');
} else {
    console.log('GET A JOB');
}

let height;

if (height) {
    console.log('IT IS DEFINED!');
} else {
    console.log('IT IS NOT DEFINED!');
}

const theAge = 17;

if (theAge === 18) console.log('You are eligible (strict equality)');
if (theAge == 18) console.log('You are eligible (loose equality)');

if (theAge !== 18) console.log('You are NOT eligible (strict equality)');
if (theAge != 18) console.log('You are NOT eligible (loose equality)');

if (theAge < 17) {
    console.log('YOUNG')
} else if (theAge > 17) {
    console.log('OLD')
}

const hasDriversLicense = true;
const hasGoodVision = false;

console.log(hasDriversLicense || hasGoodVision);
console.log(hasDriversLicense && hasGoodVision);
console.log(!hasDriversLicense);

// Switch Statement

const day = 'Monday';

switch (day) {
    case 'Monday':
        console.log('Monday is bad');
        break;
    case 'Tuesday':
        console.log('Tuesday is meh');
        break;
    case 'Wednesday':
        console.log('Wednesday is ok');
        break;
    case 'Thursday':
        console.log('Thursday is good');
        break;
    case 'Friday':
        console.log('Friday is the best');
        break;
    case 'Saturday':
        console.log('Saturday is good');
        break;
    case 'Sunday':
        console.log('Sunday is meh');
        break;
    default:
        console.log('Not a valid day!')
}

// Ternary Operator

const luckyNumber = 35;
const theNumber = luckyNumber >= 18 ? 'WHAT?' : 'HA'; // -> This is an expression not a statement like if
console.log(theNumber);


/*
Coding Challenge #1

Mark and John are trying to compare their BMI (Body Mass Index), which is calculated using the formula:

BMI = mass / height ** 2 = mass / (height * height) (mass in kg and height in meter).

Your tasks:
1. Store Mark's and John's mass and height in variables
2. Calculate both their BMIs using the formula (you can even implement both versions)
3. Create a Boolean variable 'markHasHigherBMI' containing information about whether Mark has a higher BMI than John.

Test data:
- Data 1: Marks weights 78 kg and is 1.69 m tall. John weights 92 kg and is 1.95
m tall.
- Data 2: Marks weights 95 kg and is 1.88 m tall. John weights 85 kg and is 1.76
m tall.

*/

const markMass = 95;
const markHeight = 1.88;

const johnMass = 85;
const johnHeight = 1.76

const markBMI = markMass / (markHeight * markHeight);
const johnBMI = johnMass / (johnHeight * johnHeight);

const markHasHigherBMI = markBMI > johnBMI;
console.log(markHasHigherBMI);

/*

Coding Challenge #2

Use the BMI example from Challenge #1, and the code you already wrote, and improve it.

Your tasks:

1. Print a nice output to the console, saying who has the higher BMI. The message is either "Mark's BMI is higher than John's!" or "John's BMI is higher than Mark's!"
2. Use a template literal to include the BMI values in the outputs. Example: "Mark's BMI (28.3) is higher than John's (23.9)!"

Hint: Use an if/else statement 😉

*/

if (markBMI > johnBMI) {
    console.log(`Mark's BMI (${markBMI}) is higher than John's(${johnBMI})!`);
} else {
    console.log(`John's BMI (${johnBMI}) is higher than Mark's (${markBMI})!`);
}

/*

Coding Challenge #3
There are two gymnastics teams, Dolphins and Koalas. They compete against each
other 3 times. The winner with the highest average score wins a trophy!

Your tasks:
1. Calculate the average score for each team, using the test data below
2. Compare the team's average scores to determine the winner of the competition,
and print it to the console. Don't forget that there can be a draw, so test for that
as well (draw means they have the same average score)
3. Bonus 1: Include a requirement for a minimum score of 100. With this rule, a
team only wins if it has a higher score than the other team, and the same time a
score of at least 100 points. Hint: Use a logical operator to test for minimum
score, as well as multiple else-if blocks 😉
4. Bonus 2: Minimum score also applies to a draw! So a draw only happens when
both teams have the same score and both have a score greater or equal 100
points. Otherwise, no team wins the trophy

Test data:
- Data 1: Dolphins score 96, 108 and 89. Koalas score 88, 91 and 110
- Data Bonus 1: Dolphins score 97, 112 and 101. Koalas score 109, 95 and 123
- Data Bonus 2: Dolphins score 97, 112 and 101. Koalas score 109, 95 and 106

*/













































