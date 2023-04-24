# Term Project
## Overview:
You are to create a reservation system for Premium Travel Service. The system will be used by agents of the travel agency to book trips for their clients. The trip details are given to the agent by phone as opposed to the customer booking the trip themselves.

## Requirements from the Chief Technology Officer:
* Application must be written in Java with IntelliJ
* All application data must be in JSON or XML
  * A config file must be used to change the data type
* Minimum required is a console application
  * **Large** number of bonus points if a UI is implmented using React of Angular
* Must include these patterns:
  * Strategy - to control persistence layer (JSON)
  * Factory - to create subtypes of classes
  * Singleton - to pre-load People, Place, TravelAgent, TransportType, and PaymentType
  * Decorator - to build the itenerary
  * State:
    * Accepts a Trip and updates state to determine next step
    * Awaiting Travelers
    * Awaiting Packages
    * Awaiting Payment
    * Awaiting Thank You Note
    * Itinerary Ready
* Code must be commented using JavaDoc
* Testing must be automated using JUnit
* API must be documented using Doxygen


# Project Phases
## Iteration 1 - Initial designs
The first iteration consists of:
- [X] [Use case diagram showing use cases and actors](https://github.com/OOD-TermProject/Term-Project/blob/main/Iteration1/use_case_diagram.png)
- [X] Class design for domain objects
- [X] State machine that handles creating a complete trip
- [X] One-page document describing designs

## Iteration 2 - Updated designs
Second iteration consists of:
- [X] Update class design to include design patterns
- [X] Updated class design. Must show:
  - [X] Pattern implementations
  - [X] Non-domain features (persistence, UI, etc.)
- [ ] One-page document describing designs

## Iteration 3 - Implementation
Third iteration consists of:
- [X] Coding the solution. Items required since this is a team of two:
  - [ ] XML functionality
  - [ ] JUnit testing
  - [ ] Doxygen API documentation
- [ ] Testing using the provided test data

## Iteration 4 - Presentation
Last iteration consists of:
- [ ] PowerPoint about the project
- [ ] Short demo
