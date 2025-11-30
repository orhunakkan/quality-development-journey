# Introduction to Objects

## Introduction to Objects
After learning about arrays, it is now time to explore another data structure in JavaScript: objects.

Up until this point, we have been using arrays as a data structure to store multiple related values within the same variable. Let's quickly recap that.

For example, consider Jonas's array. Note that you do not need to write this code; this is just a recap. We have the first name, and I am writing this across multiple lines, which is perfectly acceptable. Sometimes, this formatting provides a better overview of the data structure. As long as we remember to include commas after each element, we will be fine.

The array includes an age calculation, a job, and even an array of friends nested inside the main array. For example, friends include Michael, Peter, and Steven. This data structure combines multiple values that all belong to the Jonas entity.

Intuitively, the first element of the array should be called the first name, the second the last name, then age, job, and friends. However, arrays do not allow us to assign names to these elements. We cannot reference them by name, only by their order number in the array.

To solve this problem, JavaScript provides another data structure called objects. Objects allow us to define key-value pairs, giving each value a name.

Let's see how to define an object. We declare a variable, for example, named Jonas, and assign it an object. Instead of square brackets used for arrays, we use curly braces or curly brackets to define an object. This is the second time we see curly braces in JavaScript; the first time was to define code blocks in if-else statements, switch statements, or function bodies. However, here the curly braces define a new object.

We fill the object with key-value pairs. The key is essentially the variable name, followed by a colon, then the value. The value can be of any type. To define another key-value pair, we separate them with commas, similar to arrays.

For example:

firstName: Jonas  
lastName: Schmedtmann  
age: an expression that will be calculated  
job: teacher  
friends: an array copied from the previous example

With this, we have an object containing five key-value pairs.

Each key is also called a property. We say that the object named Jonas has five properties: firstName with the value Jonas, lastName with the value Schmedtmann, and so on. Objects are probably the most fundamental concept in JavaScript. There are many ways to create objects, but writing an object directly in the code using curly braces is the simplest way, called the object literal syntax because we literally write down the entire object content.

To recap, like arrays, objects group together different variables that belong together, such as the properties of Jonas. The big difference is that in objects, the order of values does not matter when retrieving them, unlike arrays where order is crucial for access.

In arrays, elements are accessed by their order number, so arrays are suitable for ordered data. Objects are better for unstructured data that we want to name and retrieve by those names. How to get data from an object will be covered in the next lecture.

## Key Takeaways
- Objects in JavaScript allow storing data as key-value pairs, enabling named access to values.
- Arrays store multiple values by order, but do not support naming elements, limiting access to index positions.
- Objects are defined using curly braces `{}` and can contain various data types, including arrays.
- The order of properties in objects does not affect data retrieval, unlike arrays where order is crucial.
- Objects are fundamental in JavaScript and can be created in multiple ways, with object literal syntax being the simplest.
