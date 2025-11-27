# JavaScript Releases and Versioning

## Introduction
Now that you are familiar with the fundamentals of the JavaScript language, we need to talk about JavaScript releases or versions before moving on. We briefly touched on this in the intro video, but now let's go into more depth because it is really important to understand how JavaScript releases work and how we can use them. This knowledge is essential for your work as a web developer and because these topics frequently arise in the developer community. You want to be able to understand what is going on.

The best way to start understanding JavaScript versions is by looking at the history of JavaScript. Let's do that and start at the beginning of the internet itself. Shortly after the internet was invented and the first two web browsers were developed, developers wanted to make websites more interactive. In other words, they needed a programming language for the browser.

## The Birth of JavaScript
In 1995, Netscape Navigator, which at the time was the dominant browser, hired Brendan Eich to create the very first version of JavaScript. Impressively, he created it in just 10 days. This initial version was called "Mocha". Although it was not yet called JavaScript, it already had many of the fundamental features that modern JavaScript has today.

In 1996, Mocha was renamed to LiveScript, and then again renamed to JavaScript for one simple reason: to attract developers from the hottest language at the time, which was Java. The "Java" in JavaScript was really just for marketing purposes because the language itself has basically nothing to do with Java. Some beginners mistakenly think otherwise, but JavaScript and Java are two completely different languages.

Also in 1996, Microsoft launched Internet Explorer, which basically copied JavaScript from Netscape but called it JScript for legal reasons, since you cannot just copy other people's programming languages. This resulted in two very similar but competing languages, which is never a good idea in the long run.

## Standardization: ECMAScript
With the internet growing rapidly around this time, people realized the need to standardize JavaScript. The language was submitted to an independent standards organization called ECMA, which in 1997 released ECMAScript 1 or ES1. This was the very first official standard for the JavaScript language. With this, every browser could implement the same standard "JavaScript."

In the real world, we usually use the term ECMAScript to refer to the standard, while JavaScript refers to the language as implemented in browsers.

Fast forward to 2009, after many complications and disagreements about the language's direction, **ES5** was released with many great new features. Then, six years later, the much-awaited **ES6** was launched in June 2015. This was the single biggest update to the language ever, containing a ton of exciting new features which we will explore throughout this course.

ES6 is also called **ES2015**, which is its official name, but most people just call it ES6. The reason for the official name ES2015 is that in 2015, ECMAScript changed to an **annual release cycle**. Now, there is a new release every year.

## Annual Releases and Backward Compatibility
According to this new release cycle:
- ES2016 (ES7) was released in 2016  
- ES2017 in 2017  
- ES2018 in 2018  
- ...and so on, indefinitely  

A unique aspect of JavaScript releases is **backward compatibility all the way back to ES1**. This means that JavaScript code written in 1997 will still work in modern browsers today. JavaScript follows the principle of **"Do Not Break the Web"**—nothing old is removed, only new features are added.

While this preserves functionality, it also means old quirks and bugs remain in the language.

## Forward Compatibility and Modern Development
JavaScript is **not** forward compatible. If we took fictional code from 2089 and ran it today, it would break. Browsers do not understand future features.

This raises the question: how can we safely use modern JavaScript when users might have old browsers?

### Two Environments
1. **Development** – You work on your computer using a modern browser like Chrome.  
2. **Production** – Users run your code on many different browsers, some outdated.

### Solution: Transpiling and Polyfilling
To ensure older browsers understand new features, developers use tools like:
- **Babel** → transpiles modern JavaScript down to ES5
- **Polyfills** → emulate missing features

Transpiling is only necessary for production, not development.

## Using JavaScript Releases Today
- **ES5** is fully supported in all browsers, including Internet Explorer 9. It is safe and used as the transpile target.
- **ES6+ (modern JavaScript)** works in all modern browsers but may require transpiling for full compatibility.
- **ESNext** represents proposals and future features not yet finalized. Browsers may implement some features early.

## Learning ES5 and ES6+
In this course:
- You learn **modern JavaScript from the start**.
- Some ES5 knowledge is still included because:
  - It helps explain how modern features work under the hood.
  - Much existing code online—and many jobs—still use ES5.
  - Many tutorials and examples are written in ES5.

## Key Takeaways
- JavaScript was created in 1995 and has evolved through many standardized releases.  
- ES5 is stable and universally supported, serving as the baseline for compatibility.  
- Modern JavaScript (ES6+) introduces powerful features but requires transpiling for older browsers.  
- JavaScript maintains backward compatibility but is not forward compatible.  
- The language evolves yearly, with new features added through the ECMAScript process.

