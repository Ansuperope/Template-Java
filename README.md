[![Java CI](https://github.com/Ansuperope/Java-Project-2-Banking-System/actions/workflows/java.yml/badge.svg)](https://github.com/Ansuperope/Java-Project-2-Banking-System/actions/workflows/java.yml)
[![Documentation](https://img.shields.io/badge/Documentation-Doxygen-blue?logo=doxygen)](https://ansuperope.github.io/Java-Project-2-Banking-System/)

## **<u>Important Links</u>**

- [<u>Original Class
  Assignment</u>](https://docs.google.com/document/d/1_azypNXBbCi_irpjK6iTcaE8TDdGcEviP4DNlee3fSo/edit?tab=t.0#heading=h.a7myqzjyjha4)

- [<u>Canvas Submit
  Link</u>](https://online.saddleback.edu/courses/30087/assignments/1077200)

- [<u>UML
  Diagrams</u>](https://drive.google.com/file/d/1_iePTjSNVr3_Zu6jHpc5LuP7scUtxnKQ/view?usp=sharing)

- [<u>Github</u>](https://github.com/Ansuperope/Java-Project-2-Banking-System)

- [<u>Google
  Doc</u>](https://docs.google.com/document/d/19Z-F2WiKFrBjV9tquQqSN_utwX2Rrgt57--4UwpNle4/edit?usp=drivesdk)

- Examples of Diagrams + About Relationships:
  [<u>GeeksforGeeks</u>](https://www.geeksforgeeks.org/system-design/unified-modeling-language-uml-class-diagrams/) +
  [<u>Creately</u>](https://creately.com/guides/class-diagram-relationships/)

## **<u>Overview</u>**

This project simulates a banking system that supports three different
types of bank accounts (CheckingAccount, SavingsAccount, CreditAccount),
which are child classes of the abstract base class BankAccount.

The BankingSystem class acts as a manager class, storing all created

bank accounts and allowing operations such as deposits, withdrawals,
transfers, and info displays for supported accounts.

The Main.java file demonstrates creating one of each BankAccount type,
performing operations on each account, displaying the info of each bank
account before and after the operations to show they were successful,
and triggering each of the exception classes for unsupported operations.

## **<u>Program</u>**

1.  ***<u>Abstract Base Class / BankAccount:</u>*** All accounts
    (derived classes) will have the following features from BankAccount

    1.  Fields:

        1.  double **amountInAccount**: how much money in account

        2.  string **accountOwner:** name of the account owner

    2.  Non Abstract Methods (will all be the same, implementation
        details included):

        1.  **BankAccount()**: default constructor

            1.  amountInAccount = 0

            2.  accountOwner = "Unknown"

        2.  **BankAccount(double amount, string name)**: default
            constructor

            1.  amountInAccount = amount

            2.  accountOwner = name

        3.  getter functions

            1.  getBalance()

            2.  getAccountOwner()

        4.  setter functions

            1.  setBalance(double amount)

            2.  setAccountOwner(string name)

        5.  void **displayAmount()**: outputs amount in account

        6.  void **displayOwner():** outputs who owns the account

    3.  Abstract Methods (will vary, not have implementation details):

        1.  **deposit(double amount)\*:** deposit funds to an account
            (pay back if a CreditAccount)

        2.  **withdraw(double amount):** withdraw funds from an account
            (borrow if a CreditAccount)

    4.  Relationship with others

        1.  **Inheritance:** derived class

2.  ***<u>Interface / InterestFeature:</u>*** Each account that allows
    you to transfer money should have the following features:

    1.  Non Abstract Methods (will all be the same, implementation
        details included):

        1.  

    2.  Abstract Methods (will vary, not have implementation details):

        1.  double **interestAmount**(): calculate the amount of
            interest

    3.  Relationship with others

        1.  **Realization:** derived class

3.  ***<u>Derived Classes:</u>*** Program will have the following
    accounts

    1.  ***<u>CheckingAccount</u>***: everyday spending. Deposit and
        withdraw money

        1.  Fields

            1.  const int **MAX_WITHDRAWLS** = 5

            2.  int **numWithdrawls**

        2.  Methods

            1.  getter functions

                1.  getNumWithdrawals()

                2.  getMaxWithdrawals()

                3.  

            2.  setter functions

                1.  **setNumWithdrawals(int numWithdrawals)**

            3.  bool **atMaxWithdrawl()**: outputs if at max or not

                1.  true = at max

                2.  false = not at max, can keep withdrawing

        3.  Relationship with others

            1.  **Inheritance:** BankAccount

            2.  **Aggregation:** BankingSystem

        4.  Special Rules (errors are thrown if these rules are
            violated)

            1.  Deposits and withdrawals must be greater than '0.0'

            2.  Withdrawals cannot exceed the account balance

            3.  Withdrawals cannot exceed the maximum withdrawal limit

    2.  ***<u>SavingAccount</u>***: store money, earn interest. If user
        has low activity they get a benefit

        1.  Fields

            1.  const **MAX_LOW_ACTIVITY** = 10

                1.  the max amount of transactions a user can do in
                    order to get the benefit of low activity

            2.  int **transactions**: keeps track of how many
                transactions done

            3.  boolean **lowActivity**: if user qualifies for low
                activity benefits

            4.  boolean **lowActivity**: true if user qualifies for low
                activity benefits

            5.  double **interestPercent**: the current interest rate of
                the account

            6.  int **timePass**: how much time has passed for interest
                calculations

        2.  Methods

            1.  getter functions

                1.  **getTransactions()**

                2.  **getLowActivity()**

                3.  **getInterestPercent()**

                4.  **getTimePass()**

                5.  **getMaxLowActivity()**

            2.  setter functions

                1.  **setTransactions(int transactions)**

                2.  **setLowActivity(boolean lowActivity)**

                3.  **setInterestPercent(double interestPercent)**

                4.  **setTimePass(int timePass)**

            3.  boolean **qualifyForLowActivity()**: calculates if user
                qualifies for low activity benefits

                1.  true = qualifies

                2.  false = does not qualify

            4.  double **lowActivityBenefit()**: calculates extra money
                low activity give user

        3.  Relationship with others

            1.  **Inheritance:** BankAccount

            2.  **Realization:** interface class

            3.  **Aggregation:** BankingSystem

        4.  Special Rules (errors are thrown if these rules are
            violated)

            1.  Deposits and withdrawals must be greater than '0.0'

            2.  Withdrawals cannot exceed the account balance

            3.  Withdrawals cannot exceed the maximum withdrawal limit

            4.  Transaction count only updates after successful deposits
                or withdrawals

            5.  Low activity status updates only after successful
                deposits or withdrawals

    3.  ***<u>CreditAccount:</u>*** borrow money

        1.  Fields

            1.  static final double MAX_LIMIT = 5000.0: the maximum
                credit limit

            2.  boolean **paidCredit**: whether the credit balance has
                been fully paid

            3.  double **interestPercent**: the current interest rate of
                the account

            4.  int **timePass**: how much time has passed for interest
                calculations

        2.  Methods

            1.  getter functions

                1.  getPaidCredit()

                2.  getInterestPercent()

                3.  getTimePass()

                4.  getMaxLimit()

            2.  setter functions

                1.  setPaidCredit(boolean paidCredit)

                2.  setInterestPercent(double interestPercent)

                3.  setTimePass(int timePass)

            3.  boolean **applyPenalty()**: determine if user needs to
                pay a penalty fee

                1.  true = user pays a fee

                2.  false = user needs to pay a fee

            4.  double **calcPenalty()**: calculates fee user needs to
                pay

        3.  Relationship with others

            1.  **Inheritance:** BankAccount

            2.  **Realization:** interface class

            3.  **Aggregation:** BankingSystem

4.  ***<u>Manager Class / BankingSystem:</u>*** will be used to do the
    following commands on all the accounts / derived classes:

    1.  Fields

        1.  **accounts**: List\<BankAccount\>

            1.  stored polymorphically, vector of all bank accounts

        2.  int **numberOfAccounts**: tracks how many accounts are in
            the system

    2.  Methods

        1.  void **addAccount(BankAccount)**: add bank account to
            manage. Will be data type abstract base class

            1.  will throw exception ErrorAccountNotFound if account not
                found

        2.  **BankAccount findAccount(String ownerName):** searches for
            an account by owner name

            1.  throws *ErrorAccountNotFound* if no account is found
                under that name

        3.  void **depositToAccount(String ownerName, double amount):**
            deposits money into an account

            1.  throws *ErrorAccountNotFound* if no account name with
                *ownerName* is found

            2.  throws *ErrorInvalidTransactionAmount* if *amount* is
                negative

        4.  void **withdrawFromAccount(String ownerName, double
            amount):** withdraws money from an account

            1.  throws *ErrorAccountNotFound* if no account name with
                *ownerName* is found

            2.  throws *ErrorInvalidTransactionAmount* if *amount* is
                negative

            3.  throws *ErrorLowFunds* if *amount* exceeds account
                balance

        5.  boolean **transferMoney()**: transfer money between
            accounts, returns *true* if successful

            1.  throws *ErrorAccountNotFound* if no account name with
                *fromOwner* or *toOwner* is found

            2.  throws *ErrorInvalidTransactionAmount* if *amount* is
                negative

            3.  throws *ErrorLowFunds* if *amount* exceeds *fromOwner*
                account balance

        6.  void **displayAccountInfo()**: outputs account info

            1.  account type

            2.  owner name

            3.  current balance

            4.  interest amount for accounts implementing
                *InterestFeature*

    3.  Relationship with others

        1.  **Aggregation (has-a, child can still exist):** manages
            multiple *BankAccount* objects

        2.  **Dependency:** custom exception classes

5.  ***<u>Exception Classes:</u>*** The manager will also call protocols
    if certain criteria are met (relationship dependencies):

    1.  **ErrorAccountNotFound**: Used when an account cannot be found
        in the banking system

    2.  **ErrorLowFunds**: Used when there are not enough funds for a
        withdrawal, when a checking account exceeds its withdrawal
        limit, or when a credit account exceeds its credit limit

    3.  **ErrorInvalidTransactionAmount**: Used when a deposit or
        withdrawal amount is less than or equal to \`0\`, or when an
        unsupported transfer/payment rule is violated.

    4.  Relationship with others

        1.  **Dependency:** used by BankingSystem and the account
            classes
