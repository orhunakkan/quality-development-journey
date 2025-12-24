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

newYears.forEach(element => { console.log(newCalcAge(element)) });

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

for (let i = 0; i < orhunArray1.length; i++) {
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

for (let i = orhunArray2.length - 1; i >= 0; i--) {
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

/*

CHALLENGE #1

Back to the two gymnastics teams, the Dolphins and the Koalas! There is a new gymnastics discipline, which works differently.
Each team competes 3 times, and then the average of the 3 scores is calculated (so one average score per team).
A team only wins if it has at least double the average score of the other team. Otherwise, no team wins!

Your tasks:

- Create an arrow function calcAverage to calculate the average of 3 scores. This function should have three parameters and return a single number (the average score).
- Create two new variables — scoreDolphins and scoreKoalas, and assign the value returned from the calcAverage function to them (you will need to call this function, and pass scores as arguments).
- Create a function checkWinner that takes the average score of each team as parameters (avgDolphins and avgKoalas), and then logs the winner to the console, together with the victory points, according to the rule above.
    Example: Koalas win (30 vs. 13) (use avgDolphins and avgKoalas instead of hard-coded values).
- Use the checkWinner function to determine the winner for both DATA 1 and DATA 2.
- Ignore draws this time. Instead, log No team wins... to the console if there is no winner.


TEST DATA 1: Dolphins scored 44, 23, and 71. Koalas scored 65, 54, and 49.
TEST DATA 2: Dolphins scored 85, 54, and 41. Koalas scored 23, 34, and 27.

*/

const calcAverage = (a, b, c) => (a + b + c) / 3;

function checkWinner(avgDolphins, avgKoalas) {
    if (avgDolphins >= 2 * avgKoalas) {
        console.log(`Dolphins win (${avgDolphins} vs. ${avgKoalas})`);
    } else if (avgKoalas >= 2 * avgDolphins) {
        console.log(`Koalas win (${avgKoalas} vs. ${avgDolphins})`);
    } else {
        console.log('No team wins...');
    }
}

// DATA 1
const scoreDolphins = calcAverage(44, 23, 71);
const scoreKoalas = calcAverage(65, 54, 49);
checkWinner(scoreDolphins, scoreKoalas);

// DATA 2
const scoreDolphinsData2 = calcAverage(85, 54, 41);
const scoreKoalasData2 = calcAverage(23, 34, 27);
checkWinner(scoreDolphinsData2, scoreKoalasData2);

/*

CHALLENGE #2

Steven wants you to improve his tip calculator.
Using the same rules as before — tip 15% of the bill if the bill value is between 50 and 300, and if the value is different, the tip is 20%.

Your tasks:

- Write a function calcTip that takes any bill value as an input and returns the corresponding tip
- Calculated based on the rules above (you can check out the code from the first tip calculator challenge if you need to).
- Use the function type you like the most. Test the function using a bill value of 100.
- And now let's use arrays! So, create an array called bills containing the test data below.
- Create an array called tips containing the tip value for each bill, calculated from the function you created before.

BONUS: Create an array totals containing the total values, so the bill + tip.

TEST DATA: 125, 555, and 44.

*/

let tip;

function calcTip(bill) {
    if (bill > 50 && bill < 300) tip = (bill * 15) / 100;
    else { tip = (bill * 20) / 100 };
    return tip;
}

console.log(calcTip(100));

const bills = [125, 555, 44];
const tips = [];
const totals = [];

bills.forEach(element => {
    tips.push(calcTip(element));
    totals.push(element + calcTip(element));
});

console.log(tips);
console.log(totals);

/*

CHALLENGE #3

Let's go back to Mark and John comparing their BMIs!
This time, let's use objects to implement the calculations! Remember: BMI = mass / (height * height) (mass in kg and height in meters).

Your tasks:

- For each of them, create an object with properties for their full name, mass, and height (Mark Miller and John Smith).
- Name these objects as mark and john, and their properties exactly as fullName, mass and height.
- Create a calcBMI method on each object to calculate the BMI (the same method on both objects).
- Assign the BMI value to a property called bmi (lowercase), and also return it from the method.
- Log to the console who has the higher BMI, together with the full name and the respective BMI.
- Example: "John Smith's BMI (28.3) is higher than Mark Miller's (23.9)!".

TEST DATA: Marks weighs 78 kg and is 1.69 m tall. John weighs 92 kg and is 1.95 m tall.

*/

// Create the objects
const mark = {
  fullName: 'Mark Miller',
  mass: 78,
  height: 1.69,
  calcBMI() {
    this.bmi = this.mass / (this.height * this.height);
    return this.bmi;
  }
};

const john = {
  fullName: 'John Smith',
  mass: 92,
  height: 1.95,
  calcBMI() {
    this.bmi = this.mass / (this.height * this.height);
    return this.bmi;
  }
};

mark.calcBMI();
john.calcBMI();

if (mark.bmi > john.bmi) {
  console.log(
    `${mark.fullName}'s BMI (${mark.bmi}) is higher than ${john.fullName}'s (${john.bmi})!`
  );
} else {
  console.log(
    `${john.fullName}'s BMI (${john.bmi}) is higher than ${mark.fullName}'s (${mark.bmi})!`
  );
}


/*

CHALLENGE #4

Let's improve Steven's tip calculator even more, this time using loops!

Your tasks:

- Create an array called bills containing all 10 test bill values.
- Create empty arrays for the tips and the totals (tips and totals)
- Use the calcTip function we wrote before (included in the starter code) to calculate tips and total values (bill + tip) for every bill value in the bills array. Use a for loop to perform the 10 calculations!

TEST DATA: 22, 295, 176, 440, 37, 105, 10, 1100, 86, and 52.

BONUS:

Write a function calcAverage which takes an array called arr as an argument.
This function calculates the average of all numbers in the given array.
This is a DIFFICULT challenge (we haven't done this before)!
Here is how to solve it if you feel like it:

- First, you will need to add up all values in the array.
- To do the addition, start by creating a variable sum that starts at 0.
- Then loop over the array using a for loop.
- In each iteration, add the current value to the sum variable.
- This way, by the end of the loop, you have all values added together.
- To calculate the average, divide the sum you calculated before by the length of the array (because that's the number of elements).
- Call the function with the totals array.

*/

const bills1 = [22, 295, 176, 440, 37, 105, 10, 1100, 86, 52];

const calcTip1 = function (bill) {
  return bill >= 50 && bill <= 300 ? bill * 0.15 : bill * 0.2;
};

const tips1 = [];
const totals1 = [];

for (let i = 0; i < bills1.length; i++) {
  const tip = calcTip1(bills1[i]);
  tips1.push(tip);
  totals1.push(bills1[i] + tip);
}

const calcAverage1 = function (arr) {
  let sum = 0;
  for (let i = 0; i < arr.length; i++) {
    sum += arr[i];
  }
  return sum / arr.length;
};

calcAverage1(totals1);
