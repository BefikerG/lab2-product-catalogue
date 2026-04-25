# Lab 2 Reflection - Product Catalogue API

### 1. Why should the ProductRequest DTO carry the @Valid annotations instead of the Product entity itself?
I used the validation on the DTO because it’s the first point of contact for the API. It doesn't make sense to let "bad" data (like a blank product name) travel all the way to my database logic before catching it. By putting `@Valid` on the DTO, the API rejects the request immediately at the Controller level. This keeps my database Entity "clean" and focused only on how data is stored, not how it's received from a user.

### 2. What is the purpose of the Location header returned on a POST 201 Created response, and which HTTP specification mandates it?
When you create something new, the `201 Created` status tells the client it worked, but the `Location` header actually tells them *where* the new product is. It basically sends back the URL (like `/api/v1/products/5`) so the client doesn't have to guess the ID. This is part of the **RFC 9110** standard, which is all about how HTTP servers should behave when resources are created.

### 3. Explain the difference between @ControllerAdvice and @ExceptionHandler. When would you use each?
- `@ExceptionHandler` is great if I only want to handle an error inside one specific controller. 
- `@ControllerAdvice` is what I used here because I wanted a "global" helper. It lets me catch exceptions from anywhere in the app in one single class. 
It’s much more efficient to use `@ControllerAdvice` because I don’t have to rewrite the same error-handling code for every new controller I add later. It keeps the error responses consistent for the whole project.

### 4. In your MockMvc tests you used @Transactional on the test class. What would happen to the database state between tests if you removed this?
If I removed `@Transactional`, the database wouldn't reset after each test. So, if one test adds a "Laptop" to the catalogue, that laptop would still be there when the next test runs. This is a problem because tests should be independent; if the database is cluttered with data from previous runs, it can cause weird failures that are hard to debug. `@Transactional` basically "undoes" every database change as soon as a test finishes.