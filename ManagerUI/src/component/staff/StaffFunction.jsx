import React from 'react'
import { FaBackward } from 'react-icons/fa'
import { Link } from 'react-router-dom'
import Sidebar from '../layout/sidebar/Sidebar'
import '../home/home.scss'
import { Nav } from 'react-bootstrap'
import Navbar from '../layout/navbar/Navbar'

const StaffFunction = () => {
    return (
        <div className='home'>
            <Sidebar />
            <div className="homeContainer">
                <Navbar />
                <div className="mt-3 ms-3">
                    <label htmlFor="">
                        <h6>Staff's services</h6>
                    </label><br></br>
                    <Link to={'/valuation-request-received'} style={{ textDecoration: 'none' }}>
                        View All ProductReceived Valuation Requests
                    </Link>
                    <br />
                    <Link to={'/requested-valuation'} style={{ textDecoration: 'none' }}>
                        View All Valuation Requests
                    </Link>

                    <div className="mt-3"> 
                        <Link to={'/manager-approved-list'} style={{ textDecoration: 'none' }}>
                            View All Manager Approved List
                        </Link>
                    </div>

                    <br />
                    <Link to={'/preliminary-valuation'} style={{ textDecoration: 'none' }}>
                        View All Preliminary Valuation Requests
                    </Link>
                    <br />
                    <Link to={'/manager-approved-List'} style={{ textDecoration: 'none' }}>
                        View All Manager Approved Valuation Requests
                    </Link>
                </div>
            </div>
        </div>
    )
}

export default StaffFunction
