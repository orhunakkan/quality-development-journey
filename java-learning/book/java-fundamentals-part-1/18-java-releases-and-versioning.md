# Java Releases and Versioning

## Introduction
Now that you are familiar with the fundamentals of the Java language, it is important to understand **how Java versions and releases work**. Java has evolved for almost three decades, and understanding its release model helps you navigate tools, documentation, features, and job requirements.

Unlike JavaScript—which is standardized as ECMAScript—Java is specified, maintained, and implemented primarily through:

- **Oracle / OpenJDK**
- **The Java Community Process (JCP)**

In this section, you will learn how Java versions came to be, how they are released, and what modern Java development looks like.

## The Birth of Java
Java was created at Sun Microsystems in the early 1990s.  
Key milestones:

- **1995** – Java 1.0 was officially released.
- Java became popular because it allowed developers to “write once, run anywhere” using the Java Virtual Machine (JVM).
- Over time, Java versions 1.1 through Java 1.4 expanded the standard library and platform features.

In 2006, Java was open-sourced as **OpenJDK**, which eventually became the official reference implementation.

## Standardization and Java Versions
Java versions were originally labeled **1.x**, such as:

- Java 1.3  
- Java 1.4  
- Java 1.5 (also called Java 5)  
- Java 1.6 (Java 6)

The naming caused confusion, so after Java 6, the version number jumped:

- **Java 7** (2011)
- **Java 8** (2014)

Java 8 was a major release introducing lambda expressions and streams—still widely used today.

## The Move to a Six-Month Release Cycle
Starting in **2017**, Java adopted a predictable six‑month release cycle.  
This means a new Java version is released **twice per year**:

- One in **March**  
- One in **September**

This resulted in modern versions such as:

- Java 9 (2017)
- Java 10 (2018)
- Java 11 (2018)
- Java 12 (2019)
- ...continuing every six months up to **Java 21**, the current LTS release.

## LTS Releases (Long-Term Support)
Not all Java versions are supported equally. Some are designated as **LTS (Long‑Term Support)** and receive updates for many years.

Current and notable LTS versions include:

- **Java 8**
- **Java 11**
- **Java 17**
- **Java 21**

Most companies run on LTS releases for stability.

## Backward Compatibility in Java
Java values **backward compatibility**.  
Most Java code written decades ago still compiles and runs today.

Examples:

- You can run Java 8 code in Java 21.
- Old APIs rarely disappear; instead, they may be marked **deprecated** before removal.

This ensures long‑lasting stability for enterprise applications.

## Forward Compatibility and Modern Development
Java is **not forward compatible**.  
Code using features from future versions cannot run on older JDKs.

For example:

- Java 21 features cannot work on Java 11.
- Java 11 features do not work on Java 8.

Developers often manage this using tools like:

- Maven or Gradle (to control compilation targets)
- Multi‑release JARs (in advanced cases)

## Using Java Versions Today

### Java 8
- Still widely used in enterprise environments.
- Many online tutorials are written with Java 8 features.

### Java 11, 17, and 21
- Recommended for modern development.
- Receive long-term support.
- Include major improvements to performance, syntax, and APIs.

### Java “Preview” and “Incubator” Features
Java also introduces experimental features that developers can opt into:

- Preview features require `--enable-preview`
- Incubator modules allow testing early APIs

These are similar to JavaScript’s “ESNext” proposals.

## Learning Java Modern Features
In this course-style material:

- You are learning **modern Java**, including features from Java 21.
- Some older Java concepts still matter because:
  - Many companies run older Java versions.
  - Legacy code is common.
  - Understanding older syntax helps you read existing codebases.

## Key Takeaways
- Java began in 1995 and evolved through many major standardized releases.
- Java has LTS releases that receive long-term support for enterprise use.
- Java maintains strong backward compatibility.
- Java is not forward compatible, so modern features require modern JDKs.
- New Java versions arrive every six months, with continual improvements.
