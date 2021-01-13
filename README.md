Presented code is a fragment of an online-shop system, where clients with their payment methods are stored in memory. System for now allows for basic operations on a set of stored data. There is also the possibility to add information about new clients in bulk. Available code consists of four classes:

- **Client**
- **PaymentMethod**
- **Core**
- **ClientsAdder**

**Client** class is created for storing data about a unique client. It should contain basic information about a customer as well as his/her payment methods.

**PaymentMethod** class as its name says is representing a single payment method which clients could use. It should contain basic information about its type (Credit card, Paypal account, etc.), addition date and expiration date.

The most general class is **Core**. It contains a set of clients and allows to manage them and to should perform many more various operations at this set (including extensions in future). For now its methods should allow to register new client, add a specific payment method to given client, calculate how many specific payment methods exists in system in total and finally it should allow to query how many payment methods will be in system in future in a specific day, accepting number of days from today as a parameter. This class is very basic now but it should be easily expandable for many new functions in future.

**ClientsAdder** class is created to be a connector between the external world and the presented system and should be used for various operations whose purpose is to add new clients to the given Core set.
Sometimes it can be used for exposing various information that can be calculated from the set of clients (e.g. calculating the sum of digits of all phone numbers that exist in a given client set) but it should not be a rule and of course different class should be responsible for these methods.

**JoinedClasses** contains all four above classes merged into one file.
