<html>
  <body>
    <h1>Retrofit Post API</h1>
    <p>The Retrofit Post API is a simple HTTP client that sends POST requests to a RESTful API. The API receives data in JSON format and returns a response in JSON format.</p>
    <h2>Features</h2>
    <ul>
      <li>Send POST requests with Retrofit HTTP client</li>
      <li>Parse JSON response with Gson library</li>
      <li>Asynchronous network calls with Coroutine support</li>
    </ul>
    <h2>Technologies Used</h2>
    <ul>
      <li>Kotlin programming language</li>
      <li>Android Studio IDE</li>
      <li>Retrofit library for HTTP requests</li>
      <li>Gson library for JSON parsing</li>
      <li>Coroutine library for asynchronous network calls</li>
    </ul>
    <h2>Installation</h2>
    <p>To use the Retrofit Post API in your Android application, follow these steps:</p>
    <ol>
      <li>Add the Retrofit, Gson, and Coroutine dependencies to your project's build.gradle file.</li>
      <li>Create a Retrofit instance with a base URL and Gson converter.</li>
      <li>Create a data class that represents the data to send in the POST request.</li>
      <li>Create a suspend function that sends the POST request and returns the response data as a parsed object.</li>
      <li>Call the function in a Coroutine scope to execute the network call asynchronously.</li>
    </ol>
  </body>
</html>
