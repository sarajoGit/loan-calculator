### Loan Calculator Application

This is a Loan Calculator application built with Spring Boot. It calculates loan details based on the provided amount, annual interest rate and loan term in months. The application also allows users to retrieve loan details and payment schedules from the database via REST API.

**Features**

- Calculate a monthly loan repayment plan: monthly payment, principal amount, interest amount and balance owed
- Save loan details and payment schedule in the database
- Retrieve loan details and payment schedule by loanId via REST APIs
- Fully RESTful APIs for GET and POST requests

**Tech Stack**

- **Backend:** Java 17, Spring Boot
- **Persistence:** Hibernate, JPA
- **Database:** H2
- **Build Tool:** Maven
- **APIs:** RESTful APIs using Spring Web

### API Endpoints

**POST /loans**

**Request Body:**
  ```json
  {
    "amount": 10,
    "annualInterestRate": 3,
    "months": 3
  }
```

**Response:**
  ```json
{
  "loanId": 1,
  "amount": 10,
  "annualInterestRate": 3,
  "numMonths": 3,
  "paymentSchedule": [
    {
      "periodNumber": 1,
      "paymentAmount": 3.35,
      "principalAmount": 3.32,
      "interestAmount": 0.03,
      "balanceOwed": 6.68
    },
    {
      "periodNumber": 2,
      "paymentAmount": 3.35,
      "principalAmount": 3.33,
      "interestAmount": 0.02,
      "balanceOwed": 3.35
    },
    {
      "periodNumber": 3,
      "paymentAmount": 3.35,
      "principalAmount": 3.34,
      "interestAmount": 0.01,
      "balanceOwed": 0
    }
  ]
}
```

**GET /loans/{loanId}**

  ```json
  {
    "amount": 10,
    "annualInterestRate": 3,
    "months": 3
  }
```

**GET /loans/{loanId}/payments**

  ```json
[
    {
        "periodNumber": 1,
        "paymentAmount": 3.35,
        "principalAmount": 3.33,
        "interestAmount": 0.02,
        "balanceOwed": 6.67
    },
    {
        "periodNumber": 2,
        "paymentAmount": 3.35,
        "principalAmount": 3.34,
        "interestAmount": 0.01,
        "balanceOwed": 3.33
    },
    {
        "periodNumber": 3,
        "paymentAmount": 3.35,
        "principalAmount": 3.34,
        "interestAmount": 0.01,
        "balanceOwed": 0.00
    }
]
```
