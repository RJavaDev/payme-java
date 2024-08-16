# Payme Integration
**Assalomu alaykum.**
This project integrates the Payme payment system into your application. The integration allows you to handle payment requests, process transactions, and manage responses from the Payme platform.
## Information

This project was developed based on the Payme documentation as of August 2024. [Payme Developer Documentation](https://developer.help/paycom.uz)

The primary function of the payment system integration involves filling in the payment system’s account number for the workshop. The core aspect of the integration is that the payment system provides information about the amount paid to our account. We then verify whether the transaction is correct or incorrect (not possible). We do not participate in the actual money transfer process; instead, we only monitor the payment.

In summary, we observe the payment process without participating in any actual funds transfer. The integration is used solely for confirming and reviewing the transaction information provided by the payment system.

## Features

- **Payment Request Handling**: Manage incoming payment requests.
- **Transaction Processing**: Process transactions using Payme's API.
- **Response Management**: Handle and store responses from the Payme system.
- **JWT Token Handling**: Support for custom JWT tokens specific to Payme.

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/RJavaDev/payme-java.git

## Testing
For more details on the Payme API, refer to the official documentation:
- [Test Payme Transactions](https://test.paycom.uz/instruction)

## Test Payme Transactions
Documentation
For more details on the Payme API, refer to the official documentation:
- [Payme Developer Documentation](https://developer.help/paycom.uz)

## Configuration

- Update the application.properties with your Payme credentials and configuration details:
```properties
  paycom.user.name=username
  paycom.user.password=key
