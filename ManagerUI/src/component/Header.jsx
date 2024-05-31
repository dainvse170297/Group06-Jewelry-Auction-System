import React from "react";
import 'bootstrap/dist/css/bootstrap.css';
import ValuationRequest from "./ValuationRequest";
import { BrowserRouter as Router, Route, Routes, Link, NavLink } from 'react-router-dom'

class Header extends React.Component {
    render() {
        return(
            // <!-- Navbar 1 - Bootstrap Brain Component -->
            <nav className="navbar navbar-expand-md bg-light bsb-navbar bsb-navbar-hover bsb-navbar-caret">
                <a className="navbar-brand" href="index.html">
                {/* <img src="./assets/img/bsb-logo.svg" alt="BootstrapBrain Logo" width="135" height="44"> */}
                </a>
                <button className="navbar-toggler border-0" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" className="bi bi-list" viewBox="0 0 16 16">
                    <path fillRule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z" />
                </svg>
                </button>
                <div className="offcanvas offcanvas-end" tabIndex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
                <div className="offcanvas-header">
                    <h5 className="offcanvas-title" id="offcanvasNavbarLabel">Menu</h5>
                    <button type="button" className="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                </div>
                <div className="offcanvas-body">
                    <ul className="navbar-nav justify-content-end flex-grow-1">
                    <li className="nav-item">
                        <a className="nav-link active" aria-current="page" href="/home">Home</a>
                    </li>
                    <li className="nav-item dropdown">
                        <a className="nav-link dropdown-toggle" href="#!" id="accountDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Auction</a>
                        <ul className="dropdown-menu border-0 shadow bsb-zoomIn" aria-labelledby="accountDropdown">
                            <li><a className="dropdown-item" href="#!">Live auction</a></li>
                            <li><a className="dropdown-item" href="#!">Upcoming auction</a></li>
                            {/* <li>
                                < className="dropdown-divider">
                            </li> */}
                            <li><a className="dropdown-item" href="#!">Past auction</a></li>
                        </ul>
                    </li>
                    <li className="nav-item dropdown">
                        <a className="nav-link dropdown-toggle" href="#!" id="servicesDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Selling</a>
                        <ul className="dropdown-menu border-0 shadow bsb-zoomIn" aria-labelledby="servicesDropdown">
                        <li><a className="dropdown-item" href="#!">About Selling</a></li>
                        <li><a className="dropdown-item" href="/create-valuation">Send Valuation Request</a></li>
                        {/* <li><a className="dropdown-item" href="#!"></a></li>
                        <li><a className="dropdown-item" href="#!"></a></li> */}
                        </ul>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#!">About</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#!">Contact</a>
                    </li>
                    </ul>
                </div>
            </div>
            </nav>
        )
    }
}

export default Header;