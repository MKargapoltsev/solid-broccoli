# Alias Name automation draft

This repository contains my practical automation draft for the **Alias Name List** task.

The goal here was not to build a full production-ready framework, but to show a clean and realistic **Java + Selenium + TestNG + Page Object Model** approach for the selected scenarios from the task. I tried to keep the solution compact, readable, and close to how I would actually structure UI automation work in a real project when the time is limited and the full DOM / runtime behavior is not available yet.

For synchronization, I used **explicit waits** in the places where asynchronous UI updates are the most likely, for example after **Save**, **Delete**, **Search**, and **Import** actions. I consider this a much healthier baseline than relying on fixed sleeps, because it makes tests less flaky and more closely tied to real UI state changes.

For selectors, I used a mixed strategy on purpose. In a real project, the first choice would be stable automation-friendly attributes such as `data-testid`, `data-qa`, stable `id`, or `name`. Since this task only provides screenshots and visible UI labels, I used **CSS selectors** where attribute-based or structural targeting is natural, and kept **XPath** for text-based controls and row-level table actions, where it is more practical and more honest given the available information.

The current scope covers selected scenarios from the task: core CRUD flow for IP records, one MAC scenario, case-insensitive search by Alias Name, CSV import replacement behavior, and two data-driven validation areas.

## Project structure

```bash
src
├── main
│   └── java
│       └── com
│           └── voipfuture
│               ├── base
│               │   ├── DriverInitialization.java
│               │   └── BasePage.java
│               ├── pageobjects
│               │   ├── AddEditAliasPage.java
│               │   ├── AliasNameListPage.java
│               │   └── DeleteAliasPopup.java
│               └── utils
│                   └── Pauses.java
└── test
    ├── java
    │   └── com
    │       └── voipfuture
    │           ├── testdata
    │           │   ├── AliasNameDataProvider.java
    │           │   └── IpAddressDataProvider.java
    │           └── tests
    │               ├── AliasNameCrudTests.java
    │               ├── AliasNameImportTests.java
    │               ├── AliasNameSearchTests.java
    │               └── AliasNameValidationTests.java
    └── resources
        └── testdata
            └── import_aliases_from_export.csv
```

## Files description

| file path | description |
| --- | --- |
| `base/DriverInitialization.java` | lightweight driver placeholder, assuming driver setup already exists |
| `base/BasePage.java` | common Selenium actions and explicit wait helpers |
| `pageobjects/AliasNameListPage.java` | main page object for IP/MAC tabs, table, search, and import |
| `pageobjects/AddEditAliasPage.java` | page object for Add/Edit Alias form |
| `pageobjects/DeleteAliasPopup.java` | small component object for delete confirmation popup |
| `tests/AliasNameCrudTests.java` | CRUD-focused tests for IP plus one MAC scenario |
| `tests/AliasNameSearchTests.java` | search-related test(s) |
| `tests/AliasNameValidationTests.java` | data-driven validation tests |
| `tests/AliasNameImportTests.java` | import-related test |
| `testdata/*.java` | TestNG data providers |
| `resources/testdata/import_aliases_from_export.csv` | CSV file used for import scenario |

## Notes

This project is intentionally kept at **automation design skeleton** level. Some selectors and final assertions may need adjustment once the real DOM and exact UI feedback are available. I preferred to keep the solution realistic and transparent instead of pretending that unknown implementation details are already confirmed.