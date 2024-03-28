// App.js
import { Route, Routes } from "react-router-dom";
import './App.css';
import { useDispatch } from 'react-redux';
import { useEffect } from 'react';
import MemberList from "./components/MemberList";
import SummaryCorona from "./components/SummaryCorona";
import NavBar from "./components/NavBar/NavBar";
import HomePage from "./components/Hompage/Hompage";
import About from "./components/About";
function App() {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch({ type: 'GET_MEMBERS' });
    dispatch({ type: 'GET_CORONA' });
  }, []);


  return (
    <>
      <NavBar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/about" element={<About />} />
        <Route path="/memberList" element={<MemberList />} />
        <Route path="/summaryCorona" element={<SummaryCorona />} />

      </Routes>
    </>
  );
}

export default App;
