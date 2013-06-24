Scenario: Open default page
When the user opens the default page
Then the user is brought to the page with '' title

Scenario: Exchange United States Dollars for Japanese Yen

When the user fills 'inputForm:fromCurrency' field with 'USD'
When the user fills 'inputForm:amount' field with '5'
When the user fills 'inputForm:toCurrency' field with 'JPY'
When clicks on element with id/name/className 'inputForm:obtainQuoteAction'
Then element id/name/className 'inputForm:quote' has text '375'