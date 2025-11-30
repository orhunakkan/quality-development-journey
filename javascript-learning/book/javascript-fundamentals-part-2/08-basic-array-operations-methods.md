# Basic Array Operations (Methods)

## Introduction to Array Methods
JavaScript provides several built-in functions that can be directly applied to arrays. These are called methods and can be thought of as array operations.

## The push Method
Let us start by bringing back the friends array from the previous lecture. The push method adds elements to the end of an array. For example, to add 'Jay' to the end of the array, use the push method. The push method is a function that is called directly on the array using the dot notation.

```javascript
friends.push('Jay')
```

After using push, the array will include the new element at the end, and the length of the array will increase. The push method also returns the new length of the array. If you want to capture this value, you can store it in a variable.

```javascript
let newLength = friends.push('Jay');
console.log(newLength);
```

## The unshift Method
Besides adding elements to the end, you can also add elements to the beginning of the array using the unshift method. For example, to add 'John' to the beginning:

```javascript
friends.unshift('John');
```

Just like push, the unshift method returns the new length of the array, but you do not always need to store it.

## Removing Elements: pop and shift
To remove elements from arrays, you can use the pop and shift methods. The pop method removes the last element of the array, while shift removes the first element.

```javascript
friends.pop();
```

The pop method does not require any arguments and returns the removed element. You can store this value if needed.

```javascript
let popped = friends.pop();
console.log(popped);
```

Similarly, to remove the first element, use the shift method. This method also returns the removed element.

```javascript
let shifted = friends.shift();
console.log(shifted);
```

## Finding Elements: indexOf and includes
To find the position of an element in an array, use the indexOf method. It returns the index of the element if found, or -1 if not found.

```javascript
console.log(friends.indexOf('Steven')); // returns 1 if 'Steven' is at index 1
console.log(friends.indexOf('Bob')); // returns -1 if 'Bob' is not found
```

A more modern and often more useful method is includes, which returns true if the element is present in the array and false otherwise. The includes method uses strict equality for comparison.

```javascript
console.log(friends.includes('Steven')); // true
console.log(friends.includes('Bob')); // false
```

If you add a number to the array and check for its string equivalent, includes will return false because it uses strict equality and does not perform type coercion.

```javascript
friends.push(23);
console.log(friends.includes('23')); // false
console.log(friends.includes(23)); // true
```

## Using includes in Conditionals
You can use the includes method in conditional statements to check if an array contains a certain element and execute code accordingly.

```javascript
if (friends.includes('Peter')) {
  console.log('You have a friend called Peter');
}
if (friends.includes('Steven')) {
  console.log('You have a friend called Steven');
}
```

## Conclusion
There are many more useful methods for manipulating arrays in JavaScript, but these are the most basic ones. Continue exploring to learn more about array operations.

## Key Takeaways
- JavaScript arrays have built-in methods for adding and removing elements, such as push, pop, unshift, and shift.
- The push and unshift methods add elements to the end and beginning of an array, respectively, and return the new length of the array.
- The pop and shift methods remove elements from the end and beginning of an array, respectively, and return the removed element.
- Methods like indexOf and includes help locate elements and check for their existence in arrays, with includes using strict equality.
