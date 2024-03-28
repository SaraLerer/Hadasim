
import React, { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import './styleNavBar.css';



export default function NavBar() {
    const [scrolling, setScrolling] = useState(false);

    useEffect(() => {
        const handleScroll = () => {
            if (window.scrollY > 0) {
                setScrolling(true);
            } else {
                setScrolling(false);
            }
        };

        window.addEventListener("scroll", handleScroll);

        return () => {
            window.removeEventListener("scroll", handleScroll);
        };
    }, []);

    return (
        <>
            <div className={`navbar-fixed ${scrolling ? 'scrolling' : ''}`}>
                <div className="navBar">
                    <NavLink id="link" to="/" style={({ isActive }) => ({ color: isActive ? '#BC8F8F' : '#0000CD' })}>home</NavLink>
                    <NavLink id="link" to="/about" style={({ isActive }) => ({ color: isActive ? '#BC8F8F' : '#0000CD' })} >About </NavLink>
                    <NavLink id="link" to="/memberList" style={({ isActive }) => ({ color: isActive ? '#BC8F8F' : '#0000CD' })} >Members </NavLink>
                    <NavLink id="link" to="/SummaryCorona" style={({ isActive }) => ({ color: isActive ? '#BC8F8F' : '#0000CD' })}>Summary-corona</NavLink>
                </div>
            </div>
        </>
    );
}
