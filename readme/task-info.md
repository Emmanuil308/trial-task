***auth-service***

1. administrator saves new user (saveNewUser):
POST "/api/auth/admin/users"

1.1. accepted argument:
@RequestBody User user;

1.2. return:
Response<SavedUserDto>

Response (package com.plaksin.authservice.model.response;)

SavedUserDto {
Long userId,
String email,
LocalDate creationDate
}

+email validation
+integration test
___________________


***quote-service***

1. administrator saves new quote (saveNewQuote):
POST "/api/quote/admin/quotes"

1.1. accepted argument:
@RequestBody Quote quote

1.2. return:
Response<QuoteDto>

Response (package com.plaksin.quoteservice.model.response;)

QuoteDto {
Long quoteId,
String content,
Long userId,
LocalDateTime time
}

+integration test
___________________

2. administrator update new quote (updateQuote):
PUT "/api/quote/admin/quotes"

2.1. accepted argument:
@RequestBody Quote quote

2.2. return:
Response<QuoteDto>

Response (package com.plaksin.quoteservice.model.response;)

QuoteDto {
Long quoteId,
String content,
Long userId,
LocalDateTime time
}

+integration test
___________________

3. get quote with information (getQuoteByIdWithInfo):
   GET "/api/quote/admin/quotes/{quoteId}"

3.1. accepted argument:
@PathVariable("quoteId") Long quoteId

3.2. return:
Response<QuoteWithInfoDto>

QuoteWithInfoDto{
Long quoteId,
String content,
LocalDateTime lastModifiedTime,
Long sumPositiveVote,
Long sumNegativeVote,
AuthorDto author
}

AuthorDto{   // from auth-service
Long id,
String email
}

+integration test
+feign client
+entity exist validation
___________________

4. administrator delete quote and votes from database (deleteQuoteById):
DELETE "/api/quote/admin/quotes/{quoteId}"

4.1. accepted argument:
@PathVariable("quoteId") Long quoteId

4.2. return:
Response<?>

+integration tes
___________________

5. get a random quote (getRandomQuote):
GET "/api/quote/admin/quotes/random"

5.1. accepted argument:
no args

5.2. return:
Response<QuoteDto>

+integration test
___________________

6. get top quotes (getTopQuotes):
GET "/api/quote/admin/quotes/top"

6.1. accepted argument:
no args

6.2. return:
Response<List<QuoteDto>>

+integration test
___________________

7. get worse quotes (getTopQuotes):
GET "/api/quote/admin/quotes/worse"

7.1. accepted argument:
no args

7.2. return:
Response<List<QuoteDto>>

+integration test
___________________