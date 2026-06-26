/* Exercise 1 - JavaScript Basics & Setup */

console.log("Welcome to the Community Portal");

window.addEventListener("load", () => {
    alert("Community Portal Loaded Successfully!");
});


/* Exercise 2 - Syntax, Data Types, and Operators */

const eventName = "Community Clean-Up";
const eventDate = "2026-07-15";
let availableSeats = 50;

console.log(
    `Event: ${eventName}, Date: ${eventDate}, Seats: ${availableSeats}`
);

availableSeats--;
console.log(`Seats Remaining: ${availableSeats}`);


/* Exercise 3 - Conditionals, Loops, and Error Handling */

const events = [
    {
        name: "Music Night",
        category: "Music",
        seats: 20,
        date: "2026-08-01"
    },
    {
        name: "Baking Workshop",
        category: "Workshop",
        seats: 15,
        date: "2026-09-01"
    },
    {
        name: "Football Tournament",
        category: "Sports",
        seats: 30,
        date: "2026-10-01"
    }
];

events.forEach(event => {
    if (
        new Date(event.date) > new Date() &&
        event.seats > 0
    ) {
        console.log(event.name);
    }
});

try {
    let seats = 5;

    if (seats <= 0) {
        throw new Error("No seats available");
    }

    seats--;
}
catch(error) {
    console.error(error.message);
}


/* Exercise 4 - Functions, Scope, Closures, Higher-Order Functions */

function addEvent(eventList, event) {
    eventList.push(event);
}

function registerUser(event) {
    if(event.seats > 0){
        event.seats--;
    }
}

function filterEventsByCategory(eventList, category){
    return eventList.filter(
        event => event.category === category
    );
}

function registrationTracker(){

    let totalRegistrations = 0;

    return function(){
        totalRegistrations++;
        return totalRegistrations;
    };
}

const trackMusicRegistrations =
    registrationTracker();


function searchEvents(callback){
    return callback(events);
}


/* Exercise 5 - Objects and Prototypes */

class Event {

    constructor(name,date,seats){

        this.name = name;
        this.date = date;
        this.seats = seats;
    }
}

Event.prototype.checkAvailability = function(){

    return this.seats > 0;
};

const workshop =
    new Event(
        "Baking Workshop",
        "2026-09-01",
        25
    );

Object.entries(workshop).forEach(
    ([key,value]) => {

        console.log(`${key}: ${value}`);
    }
);


/* Exercise 6 - Arrays and Methods */

const communityEvents = [...events];

communityEvents.push({
    name:"Coding Workshop",
    category:"Workshop",
    seats:10,
    date:"2026-11-01"
});

const musicEvents =
    communityEvents.filter(
        event => event.category === "Music"
    );

console.log(musicEvents);

const displayCards =
    communityEvents.map(
        event => `Event: ${event.name}`
    );

console.log(displayCards);


/* Exercise 7 - DOM Manipulation */

const eventContainer =
    document.querySelector("#eventContainer");

function displayEvent(event){

    const card =
        document.createElement("div");

    card.classList.add("event-card");

    card.innerHTML = `
        <h3>${event.name}</h3>
        <p>Category: ${event.category}</p>
        <p>Date: ${event.date}</p>
        <p>Seats: ${event.seats}</p>
        <button
            class="register-btn"
            onclick="registerEvent('${event.name}')">
            Register
        </button>
    `;

    eventContainer.appendChild(card);
}

function renderEvents(eventList){

    eventContainer.innerHTML = "";

    eventList.forEach(displayEvent);
}

renderEvents(communityEvents);


/* Exercise 8 - Event Handling */

document.querySelector("#registerBtn")
.onclick = function(){

    alert("Quick Registration Clicked");
};

document.querySelector("#categoryFilter")
.onchange = function(){

    const category = this.value;

    if(category === "All"){

        renderEvents(communityEvents);
    }
    else{

        renderEvents(
            filterEventsByCategory(
                communityEvents,
                category
            )
        );
    }
};

document.querySelector("#searchInput")
.addEventListener(
    "keydown",
    function(event){

        console.log(
            "Key Pressed:",
            event.key
        );
    }
);

document.querySelector("#searchInput")
.addEventListener(
    "input",
    function(){

        const keyword =
            this.value.toLowerCase();

        const filtered =
            communityEvents.filter(
                event =>
                event.name
                .toLowerCase()
                .includes(keyword)
            );

        renderEvents(filtered);
    }
);


/* Exercise 9 - Async JS, Promises, Async/Await */

fetch(
    "https://jsonplaceholder.typicode.com/posts"
)
.then(response => response.json())
.then(data =>
    console.log(
        "Promise Fetch Successful"
    )
)
.catch(error =>
    console.error(error)
);

async function fetchEvents(){

    try{

        document.querySelector("#loader")
        .style.display = "block";

        const response =
            await fetch(
                "https://jsonplaceholder.typicode.com/posts"
            );

        const data =
            await response.json();

        console.log(
            "Async Await Fetch Successful"
        );

        document.querySelector("#loader")
        .style.display = "none";
    }
    catch(error){

        console.error(error);
    }
}

fetchEvents();


/* Exercise 10 - Modern JavaScript Features */

function createEvent(
    name = "New Event"
){
    return name;
}

const sampleEvent = {

    id:1,
    title:"Community Meetup",
    location:"Park"
};

const {
    title,
    location:eventLocation
} = sampleEvent;

console.log(title);
console.log(eventLocation);

const clonedEvents =
    [...communityEvents];


/* Exercise 11 - Working with Forms */

const registrationForm =
    document.querySelector(
        "#registrationForm"
    );

registrationForm.addEventListener(
    "submit",
    function(event){

        event.preventDefault();

        const name =
            this.elements["name"].value;

        const email =
            this.elements["email"].value;

        const selectedEvent =
            this.elements["event"].value;

        const errorMsg =
            document.querySelector(
                "#errorMsg"
            );

        errorMsg.textContent = "";

        if(!name || !email){

            errorMsg.textContent =
                "All fields are required";

            return;
        }

        submitRegistration({
            name,
            email,
            event:selectedEvent
        });
    }
);


/* Exercise 12 - AJAX & Fetch API */

function submitRegistration(userData){

    const message =
        document.querySelector(
            "#message"
        );

    message.style.color = "blue";
    message.textContent =
        "Submitting...";

    setTimeout(() => {

        fetch(
            "https://jsonplaceholder.typicode.com/posts",
            {
                method:"POST",
                headers:{
                    "Content-Type":
                    "application/json"
                },
                body:
                JSON.stringify(userData)
            }
        )
        .then(response => {

            if(!response.ok){

                throw new Error(
                    "Request Failed"
                );
            }

            return response.json();
        })
        .then(data => {

            console.log(data);

            message.style.color =
                "green";

            message.textContent =
                "Registration Successful!";
        })
        .catch(error => {

            console.error(error);

            message.style.color =
                "red";

            message.textContent =
                "Registration Failed!";
        });

    },2000);
}


/* Exercise 13 - Debugging and Testing */

console.log(
    "Form Submission Started"
);

function debugRegistration(user){

    debugger;

    console.log(
        "User Data:",
        user
    );

    fetch(
        "https://jsonplaceholder.typicode.com/posts",
        {
            method:"POST",
            body:JSON.stringify(user)
        }
    );
}


/* Exercise 14 - jQuery and JS Frameworks */

$("#registerBtn").click(
    function(){

        console.log(
            "jQuery Click Triggered"
        );
    }
);

setTimeout(() => {

    $("#eventContainer")
    .fadeOut(500)
    .fadeIn(500);

},3000);


/*
Benefit of React/Vue:

Component-based architecture improves
reusability, maintainability,
and scalability.
*/


function registerEvent(eventName){

    alert(
        `Successfully Registered for ${eventName}`
    );

    trackMusicRegistrations();

    updateUI();
}


function updateUI(){

    renderEvents(communityEvents);
}