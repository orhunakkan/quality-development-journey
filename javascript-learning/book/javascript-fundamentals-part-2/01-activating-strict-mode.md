# Activating Strict Mode in JavaScript

Introduction to Strict Mode
Before continuing to learn JavaScript in this new section, it is important to activate a special mode in JavaScript called Strict Mode. This section will guide you through enabling strict mode and understanding its benefits.

Setting Up the Project
On the desktop, the folder for this part 2 fundamental section is already prepared. The starred files come from the GitHub repository shown at the beginning of the course. Use that repository as your starter folder, or copy the data elsewhere on your computer to work from there, whichever works best.

In the code editor, open the new folder. The script file is already present, and in the HTML file, the script is already linked, so there is no need to do that this time. It is assumed that you already know how to link scripts.

Activating Strict Mode
Strict mode is a special mode that can be activated in JavaScript to make it easier to write secure code. To activate strict mode, write the following string at the beginning of the script:

```javascript
'use strict';
```

With this, strict mode is activated for the entire script. It is important that this statement is the very first statement in the script. If there is any code before this, strict mode will not be activated. Comments are allowed before it, as JavaScript will ignore them, but no executable code.

Strict mode can also be activated for a specific function or block, but it is recommended to always use it at the beginning of each script.

Benefits of Strict Mode
Strict mode helps developers avoid accidental errors by:

- Forbidding certain actions.
- Creating visible errors in situations where JavaScript would otherwise fail silently.

Example: Detecting Variable Name Errors
Let us see an example of one of the most important changes that strict mode introduces. Create a variable with a complicated name, such as hasDriversLicense, and initialize it as false. Also, create a constant passTest and set it to true.

```javascript
let hasDriversLicense = false;
const passTest = true;
```

Suppose there is a person who does not have a driver's license but has just passed a test. The logic should set hasDriversLicense to true when passTest is true. Introduce a bug by misspelling the variable name, omitting the 's'.

```javascript
if (passTest) {
  hasDriverLicense = true;
}
```

Now, log a message to the console if the person has the driver's license, using the correct variable name.

```javascript
if (hasDriversLicense) {
  console.log('I can drive');
}
```

Behavior Without Strict Mode
If strict mode is deactivated and the code is run, nothing is logged to the console. Even though the intention was to set hasDriversLicense to true, the bug (missing 's') resulted in a new variable being created, leaving the original variable as false. This is a common mistake.

Behavior With Strict Mode
When strict mode is activated and the code is run again, an error is thrown: hasDriverLicense is not defined. This error message clearly indicates what is wrong, allowing the developer to spot the missing 's' and correct the variable name.

Reserved Words in Strict Mode
Strict mode introduces a short list of variable names reserved for features that might be added to the language later. For example, trying to define a variable called interface results in an error.

```javascript
const interface = 'audio';
```

This results in an error: unexpected strict mode reserved word. JavaScript reserves such words for potential future features. Another example is private.

```javascript
const private = 123;
```

This will also not work, as there might be a feature called private fields in classes or elsewhere in the language in the future. Strict mode reserves these words so they cannot be used for variables. The same logic applies to existing reserved words like if.

```javascript
const if = 1;
```

This results in an error: unexpected token 'if', as 'if' is already a reserved word for the if statement.

Other Changes in Strict Mode
There are many other changes in strict mode that affect things not yet covered, such as functions, objects, setting properties on primitive values, and more. These will be mentioned in future lectures as needed.

From now on, everything in the course will assume that strict mode is turned on. The line of code to activate strict mode will be added to all scripts in the future, and you should do the same.

Key Takeaways
- Strict mode in JavaScript helps developers write more secure code by forbidding certain actions and making errors visible.
- Strict mode must be activated by placing 'use strict'; as the first statement in a script or function.
- It prevents silent failures by throwing errors for undeclared variables and reserved words.
- Developers should always use strict mode at the beginning of their scripts to avoid common mistakes.

