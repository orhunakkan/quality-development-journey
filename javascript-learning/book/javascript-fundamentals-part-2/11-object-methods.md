# Object Methods

## Introduction to Object Methods
Let's continue exploring objects, focusing now on object methods. We have learned that objects, like arrays, can hold different types of data. They can even contain arrays or other objects nested within them. Now, we will take this concept further by adding functions as values within objects.

Since functions are values, we can create key-value pairs in objects where the value is a function. This means we can add functions to objects as properties, which are called methods.

## Adding a Method to an Object
Consider an object representing a person. Let's simplify it to include just the birth year and a Boolean indicating if the person has a driver's license. We can add a function as a property to calculate the person's age based on the birth year.

```javascript
const jonas = {
  birthYear: 1991,
  hasDriversLicense: true,
  calcAge: function(year) {
    return 2037 - year;
  }
};
```

This function expression assigned to the calcAge property calculates age by subtracting the passed-in year from 2037. This is similar to a regular function expression but now is a property of the jonas object, making it a method.

Note that we cannot use a function declaration here (e.g., function calcAge() {}) because object properties require expressions, not declarations.

## Calling Methods
We can call the method using dot notation and parentheses, just like any function call:

```javascript
jonas.calcAge(1991);
```

Alternatively, bracket notation can be used:

```javascript
jonas['calcAge'](1991);
```

Both approaches access the calcAge function property and invoke it with the argument 1991.

## Using the this Keyword
Passing the birth year as an argument is redundant since the object already contains this information. To avoid repetition and potential errors, we can access the object's own properties inside the method using the special this keyword.

The this keyword inside a method refers to the object that called the method. In this case, inside calcAge, this points to the jonas object.

We can rewrite the method without parameters, accessing `birthYear` via `this`:

```javascript
calcAge: function() {
  return 2037 - this.birthYear;
}
```

Now, calling jonas.calcAge() will compute the age using the object's own birthYear property, avoiding the need to pass it explicitly.

Logging this inside the method shows the entire jonas object, confirming that this refers to the calling object.

Using this is preferable over hardcoding the object name because if the object name changes, the method will still work correctly without modification.

## Storing Computed Properties
If a method's computation is expensive or used multiple times, it is efficient to compute it once and store the result as a new property on the object.

```javascript
calcAge: function() {
  this.age = 2037 - this.birthYear;
  return this.age;
}
```

Now, after calling jonas.calcAge(), the age property is set on the object and can be accessed directly without recalculating.

## Challenge: Creating a Summary Method
Let's create a method getSummary that returns a string summarizing the person's data, including their name, age, job, and whether they have a driver's license.

The summary string should look like:

"Jonas is a 46 year old teacher and he has a driver's license."

If hasDriversLicense is false, it should say "he has no driver's license."

## Implementing `getSummary`

```javascript
getSummary: function() {
  return `${this.firstName} is a ${this.calcAge()} year old ${this.job} and he has ${this.hasDriversLicense ? 'a' : 'no'} driver's license.`;
}
```

Calling jonas.getSummary() will return the desired summary string, dynamically using the object's properties and methods.

## Arrays are Objects with Methods
Recall that arrays have built-in methods like push, pop, shift, and unshift. This is because arrays are special kinds of objects that have functions (methods) attached to them for manipulation.

Thus, creating methods on our own objects is similar to using built-in methods on arrays, reinforcing the concept that methods are functions attached to objects.

## Key Takeaways
- Functions can be stored as values in object properties, creating methods.
- The this keyword inside a method refers to the object calling the method.
- Using this helps avoid repetition and hard-coding object names, adhering to the DRY principle.
- Methods can compute and store properties on the object for efficient reuse.
- Arrays are special objects with built-in methods, demonstrating that methods are functions attached to objects.
