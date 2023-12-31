import React, {useEffect} from 'react';
import {useNavigate} from 'react-router-dom';
import Content from './content';
import NavbarBootstrap from './navbar';
import Sidebar from './sideBar';
import sessionStorage from "sessionstorage";

const WelcomePage = () =>{
    
    const navigate  = useNavigate();

    const checkUser = () => {
        if(JSON.parse(sessionStorage.getItem('info')) === null){
            navigate('/login');
        }
    }

    useEffect(()=>{
        checkUser();

    },[JSON.parse(sessionStorage.getItem('info'))])

    return(
        <div>
            <NavbarBootstrap/>
            <div style={{display: "flex", flexDirection: "row"}}>
                <Sidebar/>
                <Content/>
            </div>
        </div>
    )
}

export default WelcomePage;