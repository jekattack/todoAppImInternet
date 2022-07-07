import React from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import MainPage from "./routes/MainPage";
import EditPage from "./routes/EditPage";
import LoginPage from "./public/LoginPage";
import RegisterPage from "./public/RegisterPage";
import Header from "./components/Header";
import OauthCallbackPage from "./components/OauthCallbackPage";
import UserDetailsPage from "./components/UserDetailsPage"

function App() {
  return (
      <div>
          <Header />
          <BrowserRouter>
              <Routes>
                  <Route path="/" element={<LoginPage />} />
                  <Route path="/register" element={<RegisterPage />} />
                  <Route path="/userdetails" element={<UserDetailsPage/>} />
                  <Route path="/kanban/" element={<MainPage />} />
                  <Route path="/kanban/:id" element={<EditPage />} />
                  <Route path={"/oauth"} element={<OauthCallbackPage />} />
              </Routes>
          </BrowserRouter>
      </div>
  );
}

export default App;
