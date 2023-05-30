# nexus-java
 Officially supported Java API for Nexus.

## Installation

In order to properly setup the repository, you need to have the Jitpack repository installed on your project. 

Maven:
```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
```

Gradle
```xml
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Then you need to add the actual dependency.

Maven:
```xml
        <dependency>
            <groupId>com.github.projnexus</groupId>
            <artifactId>nexus-java</artifactId>
            <version>1.0.0-beta-v1</version> <!-- replace with latest version -->
        </dependency>
```

Gradle:
```xml	
dependencies {
	        implementation 'com.github.projnexus:nexus-java:1.0.0-beta-v1' <!-- replace with latest version -->
}
```


When you have done this, then you should have the project setup correctly and you can move over to actually setting up your Client instance.
