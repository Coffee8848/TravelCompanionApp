# Travel Companion App

This is an Android app developed for SIT305 Task 2.1.

The app is designed to help travellers quickly convert common values such as currency, temperature, and fuel-related units.

---

## Features

### 1. Currency Conversion
Supports conversion between:
- USD
- AUD
- EUR
- JPY
- GBP

Fixed conversion rates are used as required in the assignment.

---

### 2. Temperature Conversion
Supports:
- Celsius
- Fahrenheit
- Kelvin

The app converts values by first converting to Celsius and then to the target unit.

---

### 3. Fuel & Distance Conversion
Supports three types of conversions:
- mpg ↔ km/L
- gallon ↔ liter
- nautical mile ↔ kilometer

Only valid conversion pairs are supported. Other combinations return 0.

---

## UI Components

The app includes:
- A category spinner (Currency / Temperature / Fuel)
- Two unit spinners (From / To)
- An input field for numeric values
- A Convert button
- A TextView to display results

The unit spinners update dynamically based on the selected category.

---

## Implementation Details

- Built using Java in Android Studio
- Used `Spinner` with `ArrayAdapter` for dropdown selections
- Conversion logic implemented using separate functions:
  - `convertCurrency()`
  - `convertTemperature()`
  - `convertFuel()`
- Input validation is included to prevent empty input



HAOXUAN YUAN
Student ID  225205487
SIT305
