# Dot vs. Bracket Notation in JavaScript Objects

## Introduction to Object Property Access
In this video, you will learn how to retrieve data from objects and also how to change data in objects, using both the dot and the bracket notation.

Let us actually get the object here from the previous lecture, so that Jonas object. We did not even take a look at it in the console, so let us quickly log it. You will see that it is really the same object that we just wrote in code. The only thing that is different is that the properties here are ordered alphabetically. That is what I meant in the last lecture when I said that the order of properties does not matter. That is because we simply get the properties from the object using the property name itself.

## Dot Notation
The first way of getting a property from an object is by using the dot notation, and that is very straightforward. Let us log that to the console. Let us say that we want to get the last name. All we have to do is to write Jonas.lastName and that is it. You see here is my last name coming straight from the Jonas object. This dot here is actually an operator which will go to this object and then retrieve the property with the name that we specified here.

```javascript
console.log(Jonas.lastName)
```

## Bracket Notation
We can do the exact same thing using the bracket notation. Let me show that as well. Jonas, and then we use brackets in a similar way in which we retrieve data from an array, but here we need to specify a string with the property name, so with the key. Let us do last name again, and so these results should be exactly the same.

```javascript
console.log(Jonas['lastName'])
```

## Computed Property Names with Bracket Notation
The big difference between these two is that in the bracket notation, we can actually put any expression that we would like. We do not have to explicitly write the string here, but instead we can compute it from some operation, because remember that an operation is basically an expression, something that produces a value, and so we can put that here, inside the brackets.

```javascript
let nameKey = 'Name';
console.log(Jonas['first' + nameKey]);
console.log(Jonas['last' + nameKey]);
```

Doing something like this is more common than you might think, and so that is why it is important to understand that in the square brackets, we could put any expression. The same thing would not work with the dot operator or the dot notation.

```javascript
Jonas.nameKey // This does not work
```

We have to use the real final property name and not a computed property name. For example, this one is a real property name as it appears in the object, and so that is why it works here in this case.

## When to Use Dot vs. Bracket Notation
In what situations should we use the dot notation and when do we have to use the bracket notation? When we need to first compute the property name, like we did here with the first and last name, then of course we have to use the bracket notation. In any other case, just use the dot notation, which looks a lot cleaner and is also easier to use.

## Dynamic Property Access from User Input
To make the need for the bracket notation even more clear, let me show you another example. Let us say that we do not know yet which property we want to show, and instead we get this information from some user interface. For that, we can use the prompt function.

```javascript
const interestedIn = prompt('What do you want to know about Jonas? Choose between firstName, lastName, age, job, and friends.');
console.log(interestedIn);
```

Now, what we want to do is to display the property the user chose. Can we use the dot notation?

```javascript
console.log(Jonas.interestedIn); // This will be undefined
```

We get undefined. Jonas does not have a property called 'interestedIn'.

Instead:

```javascript
console.log(Jonas[interestedIn]);
```

## Handling Non-Existent Properties
```javascript
if (Jonas[interestedIn]) {
  console.log(Jonas[interestedIn]);
} else {
  console.log('Wrong request! Choose between firstName, lastName, age, job, and friends.');
}
```

## Adding New Properties to Objects
```javascript
Jonas.location = 'Portugal';
Jonas['twitter'] = 'jonasschmedtman';
console.log(Jonas);
```

## Challenge: Dynamic Sentence Construction
```javascript
console.log(Jonas.firstName + ' has ' + Jonas.friends.length + ' friends, and his best friend is called ' + Jonas.friends[0] + '.');
```

## Operator Precedence in Property Access
The dot operator and bracket operator both have very high precedence and are executed left to right.

## Key Takeaways
- Dot notation and bracket notation are two ways to access and modify object properties in JavaScript.
- Bracket notation allows computed property names and dynamic access, while dot notation requires explicit property names.
- Attempting to access a non-existent property returns undefined, which can be handled with conditional logic.
- Both notations can be used to add new properties to objects, and understanding operator precedence is important for chaining property access.
